package com.example.mytask.roomdata

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mytask.Model.Response.ProductResponse
import com.example.mytask.Utils.ProductTypeConverter


@Database(entities = arrayOf(ProductResponse::class), version = 1, exportSchema = false)
@TypeConverters(ProductTypeConverter::class)
abstract class ProductDataSiglanton : RoomDatabase(){
    abstract fun productDao():ProductDataDao

    companion object{
        private var INSTANCE: ProductDataSiglanton? = null
        fun getInstance(context: Context): ProductDataSiglanton{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    ProductDataSiglanton::class.java,
                    "roomdb")
                    .build()
            }
            return INSTANCE as ProductDataSiglanton
        }
    }
}