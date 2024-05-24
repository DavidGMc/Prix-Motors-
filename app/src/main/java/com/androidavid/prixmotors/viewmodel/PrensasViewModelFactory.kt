package com.androidavid.prixmotors.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidavid.prixmotors.repository.PrensaRepository

class PrensasViewModelFactory(private val repository: PrensaRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PrensasViewModel(repository) as T
    }
}