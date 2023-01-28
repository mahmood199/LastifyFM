package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class TagListResponse(
    @SerializedName("toptags")
    val topTags: TopTags
)