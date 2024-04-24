package com.androidavid.clucthmaster.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.androidavid.clucthmaster.model.Disco
import com.androidavid.clucthmaster.model.Products
import com.androidavid.clucthmaster.repository.DiscoRepository

class DiscosViewModel(private val repository: DiscoRepository): ViewModel() {

    fun obtenerDiscos(): LiveData<List<Products>> {
        return repository.obtenerDiscos()

    }
}