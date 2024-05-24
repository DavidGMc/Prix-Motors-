package com.androidavid.prixmotors.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidavid.prixmotors.repository.ProductRepository

class ProductsViewModelFactory(private val repository:ProductRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductsViewModel(repository) as T
    }
}