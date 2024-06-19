package com.androidavid.prixmotors.repository

import androidx.lifecycle.LiveData
import com.androidavid.prixmotors.model.Products

interface KitRepository {

    fun obtenerKit(): LiveData<List<Products>>
}