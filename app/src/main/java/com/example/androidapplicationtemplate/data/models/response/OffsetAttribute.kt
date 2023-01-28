package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class OffsetAttribute(
    @SerializedName("num_res")
    val numRes: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int
)