package com.androidavid.prixmotors.repository

import android.content.Context
import androidx.fragment.app.FragmentManager

interface MainRepository {
    fun enviarEmail(context: Context)
    fun compartirApp(context: Context)
    fun abrirPoliticaDePrivacidad(fragmentManager: FragmentManager)
}