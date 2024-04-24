package com.androidavid.clucthmaster.repository

import androidx.lifecycle.LiveData
import com.androidavid.clucthmaster.model.Balinera
import com.androidavid.clucthmaster.model.Disco
import com.androidavid.clucthmaster.model.Products

interface RodamientoRepository {
    fun obtenerRodamientos(): LiveData<List<Products>>
}