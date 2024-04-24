package com.androidavid.clucthmaster.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.androidavid.clucthmaster.model.Prensa
import com.androidavid.clucthmaster.model.Products
import com.androidavid.clucthmaster.repository.PrensaRepository

class PrensasViewModel(private val repository: PrensaRepository): ViewModel() {

    fun obtenerPrensas(): LiveData<List<Products>> {
        return repository.obtenerPrensas()
    }
}