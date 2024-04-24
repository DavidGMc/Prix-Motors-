package com.androidavid.clucthmaster.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Products(
    val ano: String,
    val categoria_id: Int?,
    val description: String,
    val favorito: String,
    val fecha: String,
    val id: String,
    val marca: String,
    val modelo: String,
    val picture_dos: String?,
    val picture_uno: String
): Parcelable