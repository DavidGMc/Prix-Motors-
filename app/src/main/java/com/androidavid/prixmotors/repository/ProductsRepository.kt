package com.androidavid.prixmotors.repository

import androidx.lifecycle.LiveData
import com.androidavid.prixmotors.model.Products

interface ProductsRepository {
    fun obtenerProducts(): LiveData<List<Products>>
}