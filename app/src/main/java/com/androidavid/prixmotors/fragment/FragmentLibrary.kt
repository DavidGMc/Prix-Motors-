package com.androidavid.prixmotors.fragment

import InterstitialAdManager
import LibraryAdapter
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidavid.prixmotors.R
import com.androidavid.prixmotors.databinding.FragmentLibraryBinding
import com.androidavid.prixmotors.model.Category
import com.androidavid.prixmotors.ui.MainActivity
import com.androidavid.prixmotors.viewmodel.LibraryViewModel
import com.androidavid.prixmotors.viewmodel.SharedViewModel

class FragmentLibrary : Fragment(R.layout.fragment_library) {

    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!
    private lateinit var libraryViewModel: LibraryViewModel
    private lateinit var libraryAdapter: LibraryAdapter

    private lateinit var interstitialAdManager: InterstitialAdManager
    private var clickCounterPrensas = 0
    private val AD_CLICK_THRESHOLD = 3
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        interstitialAdManager = InterstitialAdManager.getInstance(requireContext())
        interstitialAdManager.loadInterstitialAd(requireActivity())
        libraryViewModel = (activity as MainActivity).libraryViewModel
        setupRecyclerView()
        observeCategories()
    }

    private fun setupRecyclerView() {
        libraryAdapter = LibraryAdapter { category ->
            navigateToCategoryFragment(category)
        }

        binding.recyclerViewCategories.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = libraryAdapter

        }
    }

    private fun observeCategories() {

        libraryViewModel.categorias.observe(viewLifecycleOwner) { categories ->
            categories?.let {
                libraryAdapter.differ.submitList(categories)
            }
        }
    }

    private fun navigateToCategoryFragment(category: Category) {
        sharedViewModel.clickCounterDis.value = (sharedViewModel.clickCounterDis.value ?: 0) + 1
        if ((sharedViewModel.clickCounterDis.value ?: 0) % AD_CLICK_THRESHOLD == 0) {
            Log.d(ContentValues.TAG, "Click = ${sharedViewModel.clickCounterDis.value}")
            interstitialAdManager.showInterstitialAd(requireActivity())
            sharedViewModel.clickCounterDis.value = 0
        } else {
            val action = when (category.id) {
                1 -> FragmentLibraryDirections.actionFragmentLibraryToFragmentPrensas()
                2 -> FragmentLibraryDirections.actionFragmentLibraryToFragmentDiscos()
                3 -> FragmentLibraryDirections.actionFragmentLibraryToFragmentRodamientos()
                4 -> FragmentLibraryDirections.actionFragmentLibraryToFragmentKitEmbragues()
                else -> throw IllegalArgumentException("Categoria no soportada: ${category.id}")
            }

            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
