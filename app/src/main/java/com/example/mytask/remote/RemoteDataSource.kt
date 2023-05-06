package com.example.mytask.remote


import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getProductData() =
        apiService.getProduct()


}