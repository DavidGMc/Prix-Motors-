package com.androidavid.prixmotors.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidavid.prixmotors.repository.RodamientoRepository

class RodamientosViewModelFactory(private val repository: RodamientoRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RodamientosViewModel(repository) as T
    }
}