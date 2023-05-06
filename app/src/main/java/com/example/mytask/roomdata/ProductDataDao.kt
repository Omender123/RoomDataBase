package com.example.mytask.roomdata

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mytask.Model.Response.ProductResponse

/*
class SearchDao {
}*/

@Dao
interface ProductDataDao{

    @Query("SELECT * FROM ProductData ")
    fun allProduct(): LiveData<List<ProductResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: ProductResponse)



}