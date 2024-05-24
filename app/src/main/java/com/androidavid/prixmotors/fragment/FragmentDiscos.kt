package com.androidavid.prixmotors.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidavid.prixmotors.MainActivity
import com.androidavid.prixmotors.R

import com.androidavid.prixmotors.adapter.ProductsAdapter
import com.androidavid.prixmotors.databinding.FragmentDiscosBinding

import com.androidavid.prixmotors.viewmodel.DiscosViewModel

class FragmentDiscos : Fragment(R.layout.fragment_discos) {
    private var _binding: FragmentDiscosBinding? = null
    private val binding get() = _binding!!
    private lateinit var discosViewModel: DiscosViewModel
    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding  = FragmentDiscosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        discosViewModel = (activity as MainActivity).discosViewModel

        setupHomeRecyclerView()
        observarDiscos()


    }
    private fun setupHomeRecyclerView(){
        productsAdapter = ProductsAdapter { product ->
            val action = FragmentDiscosDirections.actionFragmentDiscosToDetailsFragmentProducts(product)
            findNavController().navigate(action)
        }

        binding.rvDiscosFragment.apply {
            layoutManager= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter= productsAdapter
        }

    }
    private fun observarDiscos() {
        discosViewModel.obtenerDiscos().observe(viewLifecycleOwner, Observer { discos ->
            productsAdapter.differ.submitList(discos)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}