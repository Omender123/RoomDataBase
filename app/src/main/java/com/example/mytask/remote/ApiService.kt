package com.example.mytask.remote


import com.example.mytask.Model.Response.ProductResponse
import retrofit2.Response

import retrofit2.http.GET


interface ApiService {

    @GET("products")
    suspend fun getProduct(): Response<ArrayList<ProductResponse>>


}

