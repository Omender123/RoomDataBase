package com.example.mytask.Repositories

import com.example.mytask.Model.Response.BaseApiResponse
import com.example.mytask.Model.Response.ProductResponse
import com.example.mytask.Utils.NetworkResult
import com.example.mytask.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class ProductRespository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    BaseApiResponse() {
    suspend fun getProductData(): Flow<NetworkResult<ArrayList<ProductResponse>>> {
        return flow<NetworkResult<ArrayList<ProductResponse>>> {
            emit(safeApiCall { remoteDataSource.getProductData() })
        }.flowOn(Dispatchers.IO)
    }
}