package com.androidavid.prixmotors.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Int,
    val nombre: String,
    val iconoResId: String
): Parcelable

