package com.example.mytask.remote


import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getPostData() =
        apiService.getPost()


}