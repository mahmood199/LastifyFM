package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class Wiki(
    @SerializedName("summary")
    val summary: String,
    @SerializedName("content")
    val content: String

)
