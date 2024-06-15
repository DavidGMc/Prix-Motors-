package com.androidavid.prixmotors.fragment

import InterstitialAdManager
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidavid.prixmotors.R
import com.androidavid.prixmotors.adapter.ProductsAdapter
import com.androidavid.prixmotors.databinding.FragmentPrensasBinding
import com.androidavid.prixmotors.model.Products
import com.androidavid.prixmotors.ui.MainActivity
import com.androidavid.prixmotors.viewmodel.PrensasViewModel
import com.androidavid.prixmotors.viewmodel.SharedViewModel


class FragmentPrensas : Fragment(R.layout.fragment_prensas) {
    private var _binding: FragmentPrensasBinding? = null
    private val binding get() = _binding!!
    private lateinit var prensasViewModel : PrensasViewModel
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var interstitialAdManager: InterstitialAdManager
    private var clickCounterPrensas = 0
    private val AD_CLICK_THRESHOLD = 5
    private val sharedViewModel: SharedViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrensasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        interstitialAdManager = InterstitialAdManager.getInstance(requireContext())
        interstitialAdManager.loadInterstitialAd(requireActivity())
        prensasViewModel = (activity as MainActivity).prensaViewModel
        setupHomeRecyclerView()
        observarPrensas()

    }

    private fun setupHomeRecyclerView(){

        productsAdapter = ProductsAdapter { product ->
            handleProductClickPrensas(product)
        }
        binding.rvPrensasFragment.apply {
            layoutManager= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter= productsAdapter
        }
    }
    private fun observarPrensas() {
        prensasViewModel.obtenerPrensas().observe(viewLifecycleOwner, Observer { prensas ->
           productsAdapter.differ.submitList(prensas)
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun handleProductClickPrensas(product: Products) {

    sharedViewModel.clickCounterDis.value = (sharedViewModel.clickCounterDis.value ?: 0) + 1
    if ((sharedViewModel.clickCounterDis.value ?: 0) % AD_CLICK_THRESHOLD == 0) {
        Log.d(ContentValues.TAG, "Click = ${sharedViewModel.clickCounterDis.value}")
        interstitialAdManager.showInterstitialAd(requireActivity())
        sharedViewModel.clickCounterDis.value = 0
    } else {
        val action = FragmentPrensasDirections.actionFragmentPrensasToDetailsFragmentProducts(product)
        findNavController().navigate(action)
    }
}



}