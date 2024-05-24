package com.androidavid.prixmotors.repository

import androidx.lifecycle.LiveData
import com.androidavid.prixmotors.model.Products

interface PrensaRepository {
    fun obtenerPrensas(): LiveData<List<Products>>
}