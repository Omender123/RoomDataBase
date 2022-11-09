package com.example.mytask.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mytask.Model.Response.PostResponse
import com.example.mytask.Repositories.PostRespository
import com.example.mytask.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject() constructor(
    private val postRespository: PostRespository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<ArrayList<PostResponse>>> = MutableLiveData()
    val GetPostData: LiveData<NetworkResult<ArrayList<PostResponse>>> = _response

    fun fetchPostData() = viewModelScope.launch {


        postRespository.getPostData().collect { values ->
            _response.value = values
        }
    }
}