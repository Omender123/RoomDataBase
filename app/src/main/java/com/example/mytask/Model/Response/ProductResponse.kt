package com.example.mytask.Model.Response

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mytask.Utils.ProductTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ProductData")
data class ProductResponse(
    @PrimaryKey
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("price") var price: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("rating") var rating: Rating? = Rating()
) : java.io.Serializable

