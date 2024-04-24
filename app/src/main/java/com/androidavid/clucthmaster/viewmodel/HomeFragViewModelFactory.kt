package com.androidavid.clucthmaster.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidavid.clucthmaster.repository.HomeRepository

class HomeFragViewModelFactory (private val homeRepository: HomeRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeFragViewModel (homeRepository) as T
    }


}