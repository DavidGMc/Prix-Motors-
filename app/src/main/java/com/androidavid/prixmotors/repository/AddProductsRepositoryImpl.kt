package com.androidavid.prixmotors.repository

import com.androidavid.prixmotors.ClutchAPI
import com.androidavid.prixmotors.model.AddProductResponse
import retrofit2.Response

class AddProductsRepositoryImpl (private val api: ClutchAPI) : AddProductRepository{
    override suspend fun addProduct(
        key: String,
        marca: String,
        modelo: String,
        description: String,
        ano: String,
        fecha: String,
        favorito: String,
        picture_uno:String,
        picture_dos: String,
        categoriaId: Int
    ): Response<AddProductResponse> {
        return api.addProduct(
            key,
            marca,
            modelo,
            description,
            ano,
            fecha,
            favorito,
            picture_uno,
            picture_dos,
            categoriaId
        )
    }

}