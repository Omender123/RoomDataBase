package com.example.mytask.roomdata

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mytask.Model.Response.PostResponse

class PostDataViewModelOne(var application: Application): ViewModel()
{
    private val db:PostDataSiglanton = PostDataSiglanton.getInstance(application)
    internal val allpostData: LiveData<List<PostResponse>> = db.employeeDao().allEmplyees()

    fun insert(student:PostResponse){
        db.employeeDao().insert(student)
    }

    fun delete(student:PostResponse){
        db.employeeDao().delete(student)
    }
}