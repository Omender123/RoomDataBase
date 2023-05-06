package com.example.mytask.roomdata

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mytask.Model.Response.ProductResponse

class ProductDataViewModelOne(var application: Application): ViewModel()
{
    private val db:ProductDataSiglanton = ProductDataSiglanton.getInstance(application)
    internal val allProductData: LiveData<List<ProductResponse>> = db.productDao().allProduct()

    fun insert(product:ProductResponse){
        db.productDao().insert(product)
    }


}