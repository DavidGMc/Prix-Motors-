package com.androidavid.prixmotors.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.androidavid.prixmotors.model.Category
import com.androidavid.prixmotors.repository.LibraryRepository

class LibraryViewModel(private val repository: LibraryRepository): ViewModel()  {

    val categorias: LiveData<List<Category>> = repository.obtenerCategories()

}