package com.androidavid.prixmotors.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidavid.prixmotors.ClutchAPI
import com.androidavid.prixmotors.model.Products
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrensaRepositoryImpl(private val api: ClutchAPI): ProductsRepository {

    val categoryId = 1

    override fun obtenerProducts(): LiveData<List<Products>> {
        val result = MutableLiveData<List<Products>>()

        api.getProductsByCategory(categoryId).enqueue(object : Callback<List<Products>> {
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