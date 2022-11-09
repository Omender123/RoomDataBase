package com.example.mytask.roomdata

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mytask.Model.Response.PostResponse

/*
class SearchDao {
}*/

@Dao
interface PostDataDao{

    @Query("SELECT * FROM PostData ")
    fun allEmplyees(): LiveData<List<PostResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: PostResponse)


    @Delete
    fun delete(model: PostResponse)
}