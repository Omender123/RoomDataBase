package com.example.mytask.Utils

import androidx.room.TypeConverter
import com.example.mytask.Model.Response.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.util.*

class ProductTypeConverter {

    @TypeConverter
    fun fromRating(source: Rating): String {
        return JSONObject().apply {
            put("rate", source.rate)
            put("count", source.count)
        }.toString()
    }

    @TypeConverter
    fun toRating(source: String): Rating {
        val json = JSONObject(source)
        return Rating(json.get("rate") as String?, json.getString("count"))
    }
}