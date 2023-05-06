package com.example.mytask.roomdata

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ProductDataViewModelFactory(var application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductDataViewModelOne::class.java)) {
            return ProductDataViewModelOne(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}