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
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidavid.prixmotors.Config
import com.androidavid.prixmotors.FirebaseRemoteConfigHelper
import com.androidavid.prixmotors.R
import com.androidavid.prixmotors.adapter.HomeAdapter
import com.androidavid.prixmotors.databinding.FragmentHomeBinding
import com.androidavid.prixmotors.model.Products
import com.androidavid.prixmotors.ui.MainActivity
import com.androidavid.prixmotors.viewmodel.HomeFragViewModel
import com.androidavid.prixmotors.viewmodel.SharedViewModel
import com.google.firebase.remoteconfig.FirebaseRemoteConfig


class FragmentHome : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeFragViewModel: HomeFragViewModel
    private lateinit var productsPrensasAdapter: HomeAdapter
    private lateinit var productsDiscosAdapter: HomeAdapter
    private lateinit var interstitialAdManager: InterstitialAdManager
    private var mFirebaseRemoteConfig: FirebaseRemoteConfig? = null
    private var clickCounter = 0
    private val AD_CLICK_THRESHOLD = 5
    private val sharedViewModel: SharedViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        interstitialAdManager = InterstitialAdManager.getInstance(requireContext())
        interstitialAdManager.loadInterstitialAd(requireActivity())


        homeFragViewModel = (activity as MainActivity).homeFragViewModel
        setupHomeRecyclerView()
        observarProductsPrensas()
        observarProductsDiscos()
        fetchRemoteConfig()

    }
    private fun setupHomeRecyclerView(){
        productsPrensasAdapter = HomeAdapter { product ->
            handleProductClick(product)
        }

        productsDiscosAdapter = HomeAdapter { product ->
            handleProductClick(product)
        }


        binding.rvPrensas.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = productsPrensasAdapter
        }
        binding.rvDiscos.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = productsDiscosAdapter
        }

    }
    private fun observarProductsPrensas() {
        homeFragViewModel.obtenerPrensasFav().observe(viewLifecycleOwner, Observer { prensas ->
            productsPrensasAdapter.differ.submitList(prensas)
        })

    }
    private fun observarProductsDiscos() {

        homeFragViewModel.obtenerDiscosFav().observe(viewLifecycleOwner, Observer { discos ->
            productsDiscosAdapter.differ.submitList(discos)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun fetchRemoteConfig() {
        FirebaseRemoteConfigHelper.fetchRemoteConfig {
            val title_init = FirebaseRemoteConfigHelper.getString(Config.TITLE_INIT_REMOTE)
            val text_init = FirebaseRemoteConfigHelper.getString(Config.TEXT_INIT_REMOTE)
            Log.d("RemoteConfig_title", "Value from Remote Config: $title_init")
            Log.d("RemoteConfig_text", "Value from Remote Config: $text_init")
            binding.textEmbrague.text = title_init
            binding.textEmbragueDescription.text = text_init
        }
    }

    private fun handleProductClick(product: Products) {

        sharedViewModel.clickCounterDis.value = (sharedViewModel.clickCounterDis.value ?: 0) + 1
        if ((sharedViewModel.clickCounterDis.value ?: 0) % AD_CLICK_THRESHOLD == 0) {
            Log.d(ContentValues.TAG, "Click = ${sharedViewModel.clickCounterDis.value}")
            interstitialAdManager.showInterstitialAd(requireActivity())
            sharedViewModel.clickCounterDis.value = 0
        } else {
            val action = FragmentHomeDirections.actionFragmentHomeToDetailsFragmentProducts(product)
            findNavController().navigate(action)
        }
    }



}