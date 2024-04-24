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
import com.androidavid.clucthmaster.R
import com.androidavid.clucthmaster.adapter.ProductsAdapter
import com.androidavid.clucthmaster.databinding.FragmentRodamientosBinding
import com.androidavid.clucthmaster.viewmodel.RodamientosViewModel


class FragmentRodamientos : Fragment(R.layout.fragment_rodamientos) {

    private  var _binding: FragmentRodamientosBinding? = null
    private  val binding get()  = _binding!!
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var rodamientosViewModel: RodamientosViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentRodamientosBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rodamientosViewModel= (activity as MainActivity).rodamientosViewModel
        setupHomeRecyclerView()
        observarDiscos()


    }
    private fun setupHomeRecyclerView(){
        productsAdapter = ProductsAdapter { product ->
            val action = FragmentRodamientosDirections.actionFragmentRodamientosToDetailsFragmentProducts(product)
            findNavController().navigate(action)
        }

        binding.rvRodamientosFragment.apply {
            layoutManager= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter= productsAdapter
        }

    }
    private fun observarDiscos() {
        rodamientosViewModel.obtenerRodamientos().observe(viewLifecycleOwner, Observer { rodamientos ->
            productsAdapter.differ.submitList(rodamientos)
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





}