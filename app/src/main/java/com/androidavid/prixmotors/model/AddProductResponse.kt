package com.androidavid.prixmotors.model

import com.google.gson.annotations.SerializedName

data class AddProductResponse(
    @SerializedName("value") val value: Int,
    @SerializedName("message") val message: String
)
