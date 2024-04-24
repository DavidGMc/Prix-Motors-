package com.androidavid.clucthmaster.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidavid.clucthmaster.repository.DiscoRepository

class DiscosViewModelFactory(private val repository: DiscoRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DiscosViewModel(repository) as T
    }
}