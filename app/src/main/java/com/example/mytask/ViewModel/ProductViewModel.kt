package com.example.mytask.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mytask.Model.Response.ProductResponse
import com.example.mytask.Repositories.ProductRespository
import com.example.mytask.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject() constructor(
    private val ProductRespository: ProductRespository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<ArrayList<ProductResponse>>> = MutableLiveData()
    val GetProductData: LiveData<NetworkResult<ArrayList<ProductResponse>>> = _response

    fun fetchProductData() = viewModelScope.launch {


        ProductRespository.getProductData().collect { values ->
            _response.value = values
        }
    }
}