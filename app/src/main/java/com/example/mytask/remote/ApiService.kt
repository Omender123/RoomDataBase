package com.example.mytask.remote


import com.example.mytask.Model.Response.PostResponse
import retrofit2.Response

import retrofit2.http.GET


interface ApiService {

    @GET("posts")
    suspend fun getPost(): Response<ArrayList<PostResponse>>


}

