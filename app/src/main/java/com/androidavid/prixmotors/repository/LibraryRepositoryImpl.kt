package com.androidavid.prixmotors.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidavid.prixmotors.R
import com.androidavid.prixmotors.model.Category

class LibraryRepositoryImpl(private val context: Context) : LibraryRepository{

    override fun obtenerCategories(): LiveData<List<Category>> {
        val categories = listOf(
            Category(1, context.getString(R.string.menu_main_prensas), "car_repair"),
            Category(2, context.getString(R.string.menu_main_discos), "disc_full"),
            Category(3, context.getString(R.string.menu_main_balineras), "rodamientos")
            // Añade más categorías según sea necesario
        )
        return MutableLiveData(categories)
    }



}