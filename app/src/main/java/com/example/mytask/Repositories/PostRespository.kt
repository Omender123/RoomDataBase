package com.example.mytask.Repositories

import com.example.mytask.Model.Response.BaseApiResponse
import com.example.mytask.Model.Response.PostResponse
import com.example.mytask.Utils.NetworkResult
import com.example.mytask.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class PostRespository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    BaseApiResponse() {
    suspend fun getPostData(): Flow<NetworkResult<ArrayList<PostResponse>>> {
        return flow<NetworkResult<ArrayList<PostResponse>>> {
            emit(safeApiCall { remoteDataSource.getPostData() })
        }.flowOn(Dispatchers.IO)
    }
}