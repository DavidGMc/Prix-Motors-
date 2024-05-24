package com.androidavid.prixmotors.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidavid.prixmotors.model.AddProductResponse
import com.androidavid.prixmotors.repository.ProductRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductsViewModel(private val repository: ProductRepository): ViewModel() {

    fun addProduct(
        key: String,
        marca: String,
        modelo: String,
        description: String,
        ano: String,
        fecha: String,
        favorito: String,
        picture_uno:String,
        picture_dos:String,
        categoriaId: Int
    ): LiveData<Response<AddProductResponse>> {
        val responseLiveData = MutableLiveData<Response<AddProductResponse>>()
        viewModelScope.launch {
            val response = repository.addProduct(
                key,
                marca,
                modelo,
                description,
                ano,
                fecha,
                favorito,
                picture_uno,
                picture_dos,
                categoriaId
            )
            responseLiveData.postValue(response)
        }
        return responseLiveData
    }


}