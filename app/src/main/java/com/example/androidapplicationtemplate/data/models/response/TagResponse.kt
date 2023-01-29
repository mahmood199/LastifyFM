package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class TagResponse(
    @SerializedName("tag")
    val tag: Tag,
)
