package com.androidavid.clucthmaster.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.androidavid.clucthmaster.model.Products
import com.androidavid.clucthmaster.repository.HomeRepository

class HomeFragViewModel(private val homeRepository: HomeRepository): ViewModel() {
    fun obtenerPrensasFav(): LiveData<List<Products>> {
        return homeRepository.obtenerPrensasFav()

    }
    fun obtenerDiscosFav(): LiveData<List<Products>> {
        return homeRepository.obtenerDiscosFav()

    }
    fun obtenerRodamientosFav(): LiveData<List<Products>> {
        return homeRepository.obtenerRodamientosFav()

    }
}