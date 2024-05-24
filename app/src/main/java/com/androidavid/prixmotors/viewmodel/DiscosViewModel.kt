package com.androidavid.prixmotors.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.androidavid.prixmotors.model.Products
import com.androidavid.prixmotors.repository.DiscoRepository

class DiscosViewModel(private val repository: DiscoRepository): ViewModel() {

    fun obtenerDiscos(): LiveData<List<Products>> {
        return repository.obtenerDiscos()

    }
}