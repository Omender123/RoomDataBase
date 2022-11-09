package com.example.mytask.roomdata

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class PostDataViewModelFactory(var application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostDataViewModelOne::class.java)) {
            return PostDataViewModelOne(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}