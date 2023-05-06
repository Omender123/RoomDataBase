package com.example.mytask.Model.Response

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName



data class Rating(

    @SerializedName("rate") var rate: String? = null,
    @SerializedName("count") var count: String? = null

) : java.io.Serializable
