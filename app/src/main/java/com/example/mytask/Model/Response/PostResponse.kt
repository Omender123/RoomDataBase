package com.example.mytask.Model.Response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "PostData",)
data class PostResponse(

    @PrimaryKey
    @SerializedName("id"     ) var id     : Int,

    @SerializedName("userId" ) var userId : Int,
    @SerializedName("title"  ) var title  : String,
    @SerializedName("body"   ) var body   : String
) :java.io.Serializable
