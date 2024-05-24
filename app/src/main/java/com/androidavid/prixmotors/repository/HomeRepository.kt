package com.androidavid.prixmotors.repository

import androidx.lifecycle.LiveData
import com.androidavid.prixmotors.model.Products

interface HomeRepository {
    fun obtenerPrensasFav(): LiveData<List<Products>>
    fun obtenerDiscosFav(): LiveData<List<Products>>
    fun obtenerRodamientosFav(): LiveData<List<Products>>
}