package com.androidavid.clucthmaster.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.androidavid.clucthmaster.repository.MainRepository

class MainViewModel(private val repository: MainRepository): ViewModel() {
    fun compartirApp(context: Context) {
        repository.compartirApp(context)

    }

    fun enviarEmail(context: Context) {
        repository.enviarEmail(context)

    }
}