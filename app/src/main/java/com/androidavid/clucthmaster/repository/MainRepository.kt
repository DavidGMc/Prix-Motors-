package com.androidavid.clucthmaster.repository

import android.content.Context

interface MainRepository {
    fun enviarEmail(context: Context)
    fun compartirApp(context: Context)
}