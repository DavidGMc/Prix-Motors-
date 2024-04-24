package com.androidavid.clucthmaster.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidavid.clucthmaster.ClutchAPI
import com.androidavid.clucthmaster.R
import com.androidavid.clucthmaster.model.Balinera
import com.androidavid.clucthmaster.model.Disco
import com.androidavid.clucthmaster.model.Products
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RodamientoRepositoryimpl (private val api: ClutchAPI) : RodamientoRepository {
    val categoryId = 3

    override fun obtenerRodamientos(): LiveData<List<Products>> {
        val result = MutableLiveData<List<Products>>()

        api.getProductsByCategory(categoryId).enqueue(object : Callback<List<Products>> {
            override fun onResponse(
                call: Call<List<Products>>,
                response: Response<List<Products>>
            ) {
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