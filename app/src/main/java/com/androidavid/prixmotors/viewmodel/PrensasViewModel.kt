package com.androidavid.prixmotors.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.androidavid.prixmotors.model.Products
import com.androidavid.prixmotors.repository.PrensaRepository

class PrensasViewModel(private val repository: PrensaRepository): ViewModel() {

    fun obtenerPrensas(): LiveData<List<Products>> {
        return repository.obtenerPrensas()
    }
}