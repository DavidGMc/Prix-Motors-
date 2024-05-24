package com.androidavid.prixmotors.repository

import androidx.lifecycle.LiveData
import com.androidavid.prixmotors.model.Products

interface DiscoRepository {
    fun obtenerDiscos(): LiveData<List<Products>>
}