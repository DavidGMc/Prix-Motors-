package com.androidavid.prixmotors.fragment

import InterstitialAdManager
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.androidavid.prixmotors.Config
import com.androidavid.prixmotors.FirebaseRemoteConfigHelper
import com.androidavid.prixmotors.databinding.FragmentDetailsProductsBinding
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class DetailsFragmentProducts : Fragment() {

    private var _binding: FragmentDetailsProductsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailsFragmentProductsArgs>()
    private var mFirebaseRemoteConfig: FirebaseRemoteConfig? = null
    private lateinit var interstitialAdManager: InterstitialAdManager
    private var linkExtras: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        interstitialAdManager = InterstitialAdManager.getInstance(requireContext())
        interstitialAdManager.loadInterstitialAd(requireActivity())
        setupUI()
        setupShareButton()
        fetchRemoteConfig()

    }

    private fun setupUI() {
        binding.modeloTextView.text = args.products.modelo
        binding.marcaTextView.text = args.products.marca
        binding.anoTextView.text = args.products.ano
        binding.tvDescription.text = args.products.description

        // Cargar imágenes usando Coil
        binding.picture1.load(args.products.picture_uno)
        binding.picture2.load(args.products.picture_dos)
    }

    private fun setupShareButton() {
        binding.btnShareAll.setOnClickListener {
            shareProductDetails()
        }
    }
    private fun shareProductDetails() {

        CoroutineScope(Dispatchers.Main).launch {
            val imageUris = mutableListOf<Uri>()

            val uri1 = loadImageAndSaveToUri(args.products.picture_uno, "picture1_temp.jpg")
            val uri2 = loadImageAndSaveToUri(args.products.picture_dos!!, "picture2_temp.jpg")

            uri1?.let { imageUris.add(it) }
            uri2?.let { imageUris.add(it) }


            if (imageUris.isNotEmpty()) {
                val shareMessage = """
                Marca: ${args.products.marca}
                Modelo: ${args.products.modelo}
                Año: ${args.products.ano}
                Descripción: ${args.products.description}
                Información: ${linkExtras ?: ""}
            """.trimIndent()

                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND_MULTIPLE
                    type = "image/*"
                    putExtra(Intent.EXTRA_SUBJECT, "Detalles del Producto")
                    putExtra(Intent.EXTRA_TEXT, shareMessage)
                    putParcelableArrayListExtra(Intent.EXTRA_STREAM, ArrayList(imageUris))
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }

                // Mostrar el anuncio y luego lanzar el intent de compartir
                interstitialAdManager.showInterstitialAd(requireActivity()) {
                    startActivity(Intent.createChooser(shareIntent, "Compartir mediante"))
                }

            }
        }

    }




    private suspend fun loadImageAndSaveToUri(imageUrl: String, filename: String): Uri? {
        return withContext(Dispatchers.IO) {
            val request = ImageRequest.Builder(requireContext())
                .data(imageUrl)
                .build()

            val result = (requireContext().imageLoader.execute(request) as? SuccessResult)?.drawable
            result?.let { drawable ->
                val bitmapDrawable = drawable as? BitmapDrawable
                bitmapDrawable?.let {
                    saveDrawableToUri(it, filename)
                }
            }
        }
    }

    private fun saveDrawableToUri(drawable: BitmapDrawable, filename: String): Uri? {
        val bitmap = drawable.bitmap
        val file = File(requireContext().cacheDir, filename)
        return try {
            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
            stream.flush()
            stream.close()
            FileProvider.getUriForFile(requireContext(), "${requireContext().packageName}.fileprovider", file)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun fetchRemoteConfig() {
        FirebaseRemoteConfigHelper.fetchRemoteConfig {
             linkExtras = FirebaseRemoteConfigHelper.getString(Config.LINK_EXTRA_DETAILS)



        }
    }
}
