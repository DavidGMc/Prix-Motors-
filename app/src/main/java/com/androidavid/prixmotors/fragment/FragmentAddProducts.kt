package com.androidavid.prixmotors.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.androidavid.prixmotors.MainActivity
import com.androidavid.prixmotors.R
import com.androidavid.prixmotors.databinding.FragmentAddProductBinding
import com.androidavid.prixmotors.viewmodel.ProductsViewModel
import java.io.ByteArrayOutputStream


class FragmentAddProducts : Fragment(R.layout.fragment_add_product) {
    private var _binding: FragmentAddProductBinding ? = null
    private val binding get() = _binding!!
    private lateinit var productsViewModel: ProductsViewModel
    private val IMAGE_PICK_CODE = 1000
    private val PERMISSION_CODE = 1001
    private var pictureUno: String? = null
    private var pictureDos: String? = null
    private val bitmap: Bitmap? = null


      override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding  = FragmentAddProductBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pictureUno = null
        pictureDos = null

       productsViewModel = (activity as MainActivity).productsViewModel


        binding.regPictureUno.setOnClickListener {
            openGalleryForImage(REQUEST_IMAGE_ONE)
        }

        binding.regPictureDos.setOnClickListener {
            openGalleryForImage(REQUEST_IMAGE_TWO)
        }

        binding.regBtn.setOnClickListener {
            addProduct()
        }


    }
    private fun openGalleryForImage(requestCode: Int) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, requestCode)
    }

    private fun openGalleryForImageTwo() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_DENIED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                PERMISSION_CODE
            )
        } else {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_PICK_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, IMAGE_PICK_CODE)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Permission denied to access gallery",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            val uri = data.data
            uri?.let {
                val bitmap = decodeUriToBitmap(uri)
                when (requestCode) {
                    REQUEST_IMAGE_ONE -> {
                        binding.regPictureUno.setImageBitmap(bitmap)
                        pictureUno = compressImage(bitmap)
                    }
                    REQUEST_IMAGE_TWO -> {
                        binding.regPictureDos.setImageBitmap(bitmap)
                        pictureDos = compressImage(bitmap)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun decodeUriToBitmap(uri: Uri): Bitmap {
        val inputStream = requireActivity().contentResolver.openInputStream(uri)
        return BitmapFactory.decodeStream(inputStream)
    }

    private fun compressImage(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)
        val imageBytes = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }
    private fun addProduct() {
        val marca = binding.regMarca.text.toString().trim()
        val modelo = binding.regModelo.text.toString().trim()
        val description = binding.regDescription.text.toString().trim()
        val ano = binding.regAno.text.toString().trim()
        val categoriaId = binding.regCategoria.text.toString().toIntOrNull() ?: return
        val favorito = binding.favorito.isChecked.toString() // Convertir el estado del CheckBox a String

        Log.d("AddProduct", "Parámetros enviados a la API: Marca=$marca, Modelo=$modelo, Descripción=$description, Año=$ano, Categoría ID=$categoriaId, Favorito=$favorito, Picture_uno =$pictureUno, Picture_Dos = $pictureDos")

        if (marca.isBlank() || modelo.isBlank() || description.isBlank() || ano.isBlank() || categoriaId == null) {
            Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }



        productsViewModel.addProduct(
            key = "insert",
            marca = marca,
            modelo = modelo,
            description = description,
            ano = ano,
            fecha = "2024/03/01",
            favorito = favorito,
            picture_uno = pictureUno ?: "", // Si pictureUno es null, se asigna un valor por defecto (cadena vacía)
            picture_dos = pictureDos ?: "",
            categoriaId = categoriaId,


        ).observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                Log.d("AddProduct", "Respuesta de la API: Valor=${response.code()}, Mensaje=${response.message()}")

                Toast.makeText(
                    requireContext(),
                    "Producto agregado exitosamente",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Log.e("AddProduct", "Error en la respuesta de la API: ${response.code()}")

                Toast.makeText(requireContext(), "Error al agregar el producto", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    companion object {
        private const val REQUEST_IMAGE_ONE = 1
        private const val REQUEST_IMAGE_TWO = 2
    }


}