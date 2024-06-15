package com.androidavid.prixmotors

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {



    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Config.BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}