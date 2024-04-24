package com.androidavid.clucthmaster.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidavid.clucthmaster.repository.RodamientoRepository
import com.androidavid.clucthmaster.repository.RodamientoRepositoryimpl

class RodamientosViewModelFactory(private val repository: RodamientoRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RodamientosViewModel(repository) as T
    }
}