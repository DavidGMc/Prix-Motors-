package com.androidavid.prixmotors.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidavid.prixmotors.repository.LibraryRepository

class LibraryViewModelFactory(private val repository: LibraryRepository): ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LibraryViewModel (repository) as T
    }
}