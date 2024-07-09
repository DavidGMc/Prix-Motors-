package com.androidavid.prixmotors.repository

import com.androidavid.prixmotors.model.AddProductResponse
import retrofit2.Response

interface AddProductRepository {
    suspend fun addProduct(
        key: String,
        marca: String,
        modelo: String,
        description: String,
        ano: String,
        fecha: String,
        favorito: String,
        picture_uno: String,
        picture_dos: String,
        categoriaId: Int
    ): Response<AddProductResponse>
}