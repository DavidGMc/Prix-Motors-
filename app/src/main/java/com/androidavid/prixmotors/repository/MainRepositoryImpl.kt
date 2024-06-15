package com.androidavid.prixmotors.repository

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.androidavid.prixmotors.R
import com.androidavid.prixmotors.fragment.FragmentPolicyPrivacy

class MainRepositoryImpl: MainRepository {
    override fun enviarEmail(context: Context) {
        val destino = "contact@androidavid.com"

        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(destino))
        intent.putExtra(Intent.EXTRA_SUBJECT,"")
        intent.putExtra(Intent.EXTRA_TEXT, "")
        intent.type = "message/rfc822"

        try {
            context.startActivity(Intent.createChooser(intent,
                context.getString(R.string.enviar_email)))
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
            context.getString(R.string.message_link_download)
        )
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.compartir_con)))
    }
    override fun abrirPoliticaDePrivacidad(fragmentManager: FragmentManager) {
        val fragment = FragmentPolicyPrivacy()
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack(null)
            .commit()
    }
}