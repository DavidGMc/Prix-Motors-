package com.androidavid.prixmotors.repository

import androidx.lifecycle.LiveData
import com.androidavid.prixmotors.model.Products

interface RodamientoRepository {
    fun obtenerRodamientos(): LiveData<List<Products>>
}