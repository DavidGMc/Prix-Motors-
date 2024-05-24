package com.androidavid.prixmotors.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidavid.prixmotors.repository.HomeRepository

class HomeFragViewModelFactory (private val homeRepository: HomeRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeFragViewModel (homeRepository) as T
    }


}