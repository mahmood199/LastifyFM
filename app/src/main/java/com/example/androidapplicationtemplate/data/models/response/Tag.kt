package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("count")
    val count: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("reach")
    val reach: Int,
    @SerializedName("wiki")
    val wiki: Wiki,
)