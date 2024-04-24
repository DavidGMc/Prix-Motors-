package com.androidavid.clucthmaster.fragment

import InterstitialAdManager
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidavid.clucthmaster.Config
import com.androidavid.clucthmaster.FirebaseRemoteConfigHelper
import com.androidavid.clucthmaster.MainActivity
import com.androidavid.clucthmaster.adapter.HomeAdapter
import com.androidavid.clucthmaster.databinding.FragmentHomeBinding
import com.androidavid.clucthmaster.viewmodel.HomeFragViewModel
import com.google.firebase.remoteconfig.FirebaseRemoteConfig


class FragmentHome : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeFragViewModel: HomeFragViewModel
    private lateinit var productsPrensasAdapter: HomeAdapter
    private lateinit var productsDiscosAdapter: HomeAdapter
    private lateinit var interstitialAdManager: InterstitialAdManager
    private var mFirebaseRemoteConfig: FirebaseRemoteConfig? = null
    private var clickCounter = 0
    private val AD_CLICK_THRESHOLD = 10



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
        clickCounter++


        if (clickCounter % AD_CLICK_THRESHOLD == 0) {
            Log.d(TAG, "Click = $clickCounter")

            interstitialAdManager.showInterstitialAd(requireActivity())

            clickCounter = 0
        } else {
            productsPrensasAdapter= HomeAdapter { product ->
                product.let {
                    val action = FragmentHomeDirections.actionFragmentHomeToDetailsFragmentProducts(product)
                    findNavController().navigate(action)
                }

            }
        }

        productsDiscosAdapter= HomeAdapter{product ->
            product.let {
                val action = FragmentHomeDirections.actionFragmentHomeToDetailsFragmentProducts(product)
                findNavController().navigate(action)
            }

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



}