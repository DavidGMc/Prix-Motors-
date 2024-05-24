package com.androidavid.prixmotors.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.androidavid.prixmotors.model.Products
import com.androidavid.prixmotors.repository.RodamientoRepository

class RodamientosViewModel(private val repository: RodamientoRepository): ViewModel() {

    fun obtenerRodamientos() : LiveData<List<Products>> {

        return repository.obtenerRodamientos()

    }
}