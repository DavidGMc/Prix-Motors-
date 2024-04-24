package com.androidavid.clucthmaster.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.androidavid.clucthmaster.model.Balinera
import com.androidavid.clucthmaster.model.Products
import com.androidavid.clucthmaster.repository.RodamientoRepository

class RodamientosViewModel(private val repository: RodamientoRepository): ViewModel() {

    fun obtenerRodamientos() : LiveData<List<Products>> {

        return repository.obtenerRodamientos()

    }
}