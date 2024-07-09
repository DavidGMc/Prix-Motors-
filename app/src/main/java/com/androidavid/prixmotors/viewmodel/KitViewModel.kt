package com.androidavid.prixmotors.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.androidavid.prixmotors.model.Products
import com.androidavid.prixmotors.repository.ProductsRepository

class KitViewModel(private val repository: ProductsRepository): ViewModel()  {

    fun obtenerKit(): LiveData<List<Products>> {
        return repository.obtenerProducts()

    }

}