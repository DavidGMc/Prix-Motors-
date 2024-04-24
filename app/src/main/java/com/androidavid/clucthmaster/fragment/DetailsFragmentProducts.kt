package com.androidavid.clucthmaster.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.navigation.fragment.navArgs
import coil.load
import com.androidavid.clucthmaster.databinding.FragmentDetailsProductsBinding


class DetailsFragmentProducts : Fragment() {

    private var _binding: FragmentDetailsProductsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailsFragmentProductsArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        observeData()
    }

    private fun setupUI() {

        binding.modeloTextView.text = args.products.modelo
        binding.marcaTextView.text = args.products.marca
        binding.picture1.load(args.products.picture_uno)
        binding.picture2.load(args.products.picture_dos)
        binding.anoTextView.text = args.products.ano
        binding.tvDescription.text= args.products.description


    }

    private fun observeData() {
        // Aquí puedes observar cualquier dato necesario desde el ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
