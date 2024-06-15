package com.androidavid.prixmotors.fragment

import LibraryAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidavid.prixmotors.R
import com.androidavid.prixmotors.databinding.FragmentLibraryBinding
import com.androidavid.prixmotors.model.Category
import com.androidavid.prixmotors.ui.MainActivity
import com.androidavid.prixmotors.viewmodel.LibraryViewModel

class FragmentLibrary : Fragment(R.layout.fragment_library) {

    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!
    private lateinit var libraryViewModel: LibraryViewModel
    private lateinit var libraryAdapter: LibraryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        libraryViewModel.categorias.observe(viewLifecycleOwner, Observer { categories ->
            libraryAdapter.differ.submitList(categories)
        })
    }

    private fun navigateToCategoryFragment(category: Category) {
        val action = when (category.id) {
            1 -> FragmentLibraryDirections.actionFragmentLibraryToFragmentPrensas()
            2 -> FragmentLibraryDirections.actionFragmentLibraryToFragmentDiscos()
            3 -> FragmentLibraryDirections.actionFragmentLibraryToFragmentRodamientos()
            else -> throw IllegalArgumentException("Categoria no soportada: ${category.id}")
        }

        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
