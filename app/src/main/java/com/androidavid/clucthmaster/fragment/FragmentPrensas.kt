package com.androidavid.clucthmaster.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidavid.clucthmaster.MainActivity
import com.androidavid.clucthmaster.adapter.ProductsAdapter
import com.androidavid.clucthmaster.databinding.FragmentPrensasBinding
import com.androidavid.clucthmaster.viewmodel.PrensasViewModel


class FragmentPrensas : Fragment() {
    private var _binding: FragmentPrensasBinding? = null
    private val binding get() = _binding!!
    private lateinit var prensasViewModel : PrensasViewModel
    private lateinit var productsAdapter: ProductsAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrensasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prensasViewModel = (activity as MainActivity).prensaViewModel
        setupHomeRecyclerView()
        observarPrensas()

    }

    private fun setupHomeRecyclerView(){
        productsAdapter = ProductsAdapter { product ->
            product?.let {
                val action = FragmentPrensasDirections.actionFragmentPrensasToDetailsFragmentProducts(product)
                findNavController().navigate(action)
            }
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



}