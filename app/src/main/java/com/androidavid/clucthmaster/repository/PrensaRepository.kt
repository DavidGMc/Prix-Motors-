package com.androidavid.clucthmaster.repository

import androidx.lifecycle.LiveData
import com.androidavid.clucthmaster.model.Prensa
import com.androidavid.clucthmaster.model.Products

interface PrensaRepository {
    fun obtenerPrensas(): LiveData<List<Products>>
}