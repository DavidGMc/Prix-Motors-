package com.androidavid.prixmotors.fragment

import InterstitialAdManager
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidavid.prixmotors.R
import com.androidavid.prixmotors.adapter.ProductsAdapter
import com.androidavid.prixmotors.databinding.FragmentKitEmbraguesBinding
import com.androidavid.prixmotors.model.Products
import com.androidavid.prixmotors.ui.MainActivity
import com.androidavid.prixmotors.viewmodel.KitViewModel
import com.androidavid.prixmotors.viewmodel.SharedViewModel


class FragmentKitEmbragues : Fragment(R.layout.fragment_kit_embragues) {

    private var _binding: FragmentKitEmbraguesBinding? = null
    private val binding get() = _binding!!
    private lateinit var kitViewModel : KitViewModel
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var interstitialAdManager: InterstitialAdManager
    private var clickCounterPrensas = 0
    private val AD_CLICK_THRESHOLD = 3
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKitEmbraguesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        interstitialAdManager = InterstitialAdManager.getInstance(requireContext())
        interstitialAdManager.loadInterstitialAd(requireActivity())
        kitViewModel = (activity as MainActivity).kitViewModel
        setupHomeRecyclerView()

        kitViewModel.obtenerKit().observe(viewLifecycleOwner, Observer { products ->
            productsAdapter.submitList(products)
        })

        // Configurar la barra de búsqueda

        binding.searchViewProducts.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                productsAdapter.filter.filter(newText)
                return true
            }
        })
    }
    private fun setupHomeRecyclerView(){

        productsAdapter = ProductsAdapter { product ->
            handleProductClickPrensas(product)
        }
        binding.rvKitFragment.apply {
            layoutManager= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter= productsAdapter
        }
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
            val action =
                FragmentKitEmbraguesDirections.actionFragmentKitEmbraguesToDetailsFragmentProducts(product)
            findNavController().navigate(action)
        }
    }

}