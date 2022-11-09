package com.example.mytask.roomdata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mytask.Model.Response.PostResponse


@Database(entities = arrayOf(PostResponse::class), version = 1, exportSchema = false)
abstract class PostDataSiglanton : RoomDatabase(){
    abstract fun employeeDao():PostDataDao

    companion object{
        private var INSTANCE: PostDataSiglanton? = null
        fun getInstance(context: Context): PostDataSiglanton{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    PostDataSiglanton::class.java,
                    "roomdb")
                    .build()
            }
            return INSTANCE as PostDataSiglanton
        }
    }
}