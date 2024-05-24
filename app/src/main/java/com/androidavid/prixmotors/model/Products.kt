package com.androidavid.prixmotors.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Products(

    val id: Int,
    val marca: String,
    val modelo: String,
    val description: String,
    val ano: String,
    val fecha: String,
    val favorito: Boolean,
    val picture_uno: String,
    val picture_dos: String?,
    val categoriaId: Int
): Parcelable