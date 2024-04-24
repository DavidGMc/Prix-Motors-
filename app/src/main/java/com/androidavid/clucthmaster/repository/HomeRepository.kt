package com.androidavid.clucthmaster.repository

import androidx.lifecycle.LiveData
import com.androidavid.clucthmaster.model.Products

interface HomeRepository {
    fun obtenerPrensasFav(): LiveData<List<Products>>
    fun obtenerDiscosFav(): LiveData<List<Products>>
    fun obtenerRodamientosFav(): LiveData<List<Products>>
}