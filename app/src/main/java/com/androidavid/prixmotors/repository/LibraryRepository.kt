package com.androidavid.prixmotors.repository

import androidx.lifecycle.LiveData
import com.androidavid.prixmotors.model.Category

interface LibraryRepository {

    fun obtenerCategories(): LiveData<List<Category>>
}