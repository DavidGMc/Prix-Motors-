package com.androidavid.clucthmaster.repository

import android.content.Context
import android.content.Intent
import android.util.Log

class MainRepositoryImpl: MainRepository {
    override fun enviarEmail(context: Context) {
        val destino = "Davidapps2016@gmail.com"

        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(destino))
        intent.putExtra(Intent.EXTRA_SUBJECT,"")
        intent.putExtra(Intent.EXTRA_TEXT, "")
        intent.type = "message/rfc822"

        try {
            context.startActivity(Intent.createChooser(intent, "Enviar email"))
            Log.i("EMAIL", "enviando Email: ")
        } catch (e: Exception) {
            // Maneja cualquier excepción
            Log.e("EMAIL", "Error al enviar el email: ${e.message}")
        }
    }

    override fun compartirApp(context: Context) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "Te recomiendo esta app de Embragues (Clucth) https://play.google.com/store/apps/details?id=es.prix"
        )
        context.startActivity(Intent.createChooser(intent, "Compartir con"))
    }
}