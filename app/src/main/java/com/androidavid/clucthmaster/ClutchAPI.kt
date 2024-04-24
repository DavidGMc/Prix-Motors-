package com.androidavid.clucthmaster

import com.androidavid.clucthmaster.model.Prensa
import com.androidavid.clucthmaster.model.Products
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ClutchAPI {
    @GET("get_products.php")
    fun getProducts(): Call<List<Products>>

    @GET("get_productsByCategory.php")
    fun getProductsByCategory(@Query("categoria_id") categoryId: Int): Call<List<Products>>

    @GET("get_favs_prod_by_category.php")
    fun getFavoriteProductsByCategory(
        @Query("categoria_id") categoryId: Int,
        @Query("favorito") favorito: Boolean = true
    ): Call<List<Products>>




}