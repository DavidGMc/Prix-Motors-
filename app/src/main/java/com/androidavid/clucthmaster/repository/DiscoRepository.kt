package com.androidavid.clucthmaster.repository

import androidx.lifecycle.LiveData
import com.androidavid.clucthmaster.model.Disco
import com.androidavid.clucthmaster.model.Products

interface DiscoRepository {
    fun obtenerDiscos(): LiveData<List<Products>>
}