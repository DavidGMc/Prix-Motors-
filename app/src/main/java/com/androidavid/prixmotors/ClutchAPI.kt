package com.androidavid.prixmotors

import com.androidavid.prixmotors.model.AddProductResponse
import com.androidavid.prixmotors.model.Category
import com.androidavid.prixmotors.model.Products
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ClutchAPI {

    @GET("get_all_categories.php")
    fun getAllCategories(): Call<List<Category>>
    @GET("get_products.php")
    fun getProducts(): Response<List<Products>>

    @GET("get_productsByCategory.php")
    fun getProductsByCategory(@Query("categoria_id") categoryId: Int): Call<List<Products>>

    @GET("get_favs_prod_by_category.php")
    fun getFavoriteProductsByCategory(
        @Query("categoria_id") categoryId: Int,
        @Query("favorito") favorito: Boolean = true
    ): Call<List<Products>>

    @FormUrlEncoded
    @POST("add_Product.php")
    suspend fun addProduct(
        @Field("key") key: String,
        @Field("marca") marca: String,
        @Field("modelo") modelo: String,
        @Field("description") description: String,
        @Field("ano") ano: String,
        @Field("fecha") fecha: String,
        @Field("favorito") favorito: String,
        @Field("picture_uno") picture_uno: String,
        @Field("picture_dos") picture_dos: String,
        @Field("categoria_id") categoriaId: Int
    ): Response<AddProductResponse>

}