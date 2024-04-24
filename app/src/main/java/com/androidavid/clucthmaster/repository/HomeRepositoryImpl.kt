package com.androidavid.clucthmaster.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidavid.clucthmaster.ClutchAPI
import com.androidavid.clucthmaster.model.Products
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepositoryImpl(private val api: ClutchAPI): HomeRepository {
    override fun obtenerPrensasFav(): LiveData<List<Products>> {
        val categoryId = 1
        val result = MutableLiveData<List<Products>>()

        api.getFavoriteProductsByCategory(categoryId).enqueue(object : Callback<List<Products>> {
            override fun onResponse(call: Call<List<Products>>, response: Response<List<Products>>) {
                if (response.isSuccessful) {
                    result.postValue(response.body())

                } else {

                }
            }

            override fun onFailure(call: Call<List<Products>>, t: Throwable) {

            }
        })

        return result
    }

    override fun obtenerDiscosFav(): LiveData<List<Products>> {
        val categoryId = 2
        val result = MutableLiveData<List<Products>>()

        api.getFavoriteProductsByCategory(categoryId).enqueue(object : Callback<List<Products>> {
            override fun onResponse(call: Call<List<Products>>, response: Response<List<Products>>) {
                if (response.isSuccessful) {
                    result.postValue(response.body())

                } else {

                }
            }

            override fun onFailure(call: Call<List<Products>>, t: Throwable) {

            }
        })

        return result
    }

    override fun obtenerRodamientosFav(): LiveData<List<Products>> {
        val categoryId = 3
        val result = MutableLiveData<List<Products>>()

        api.getFavoriteProductsByCategory(categoryId).enqueue(object : Callback<List<Products>> {
            override fun onResponse(call: Call<List<Products>>, response: Response<List<Products>>) {
                if (response.isSuccessful) {
                    result.postValue(response.body())

                } else {

                }
            }

            override fun onFailure(call: Call<List<Products>>, t: Throwable) {

            }
        })

        return result
    }
}