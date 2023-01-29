package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("name")
    val name: String,
    @SerializedName("reach")
    val reach: Int = 0
)