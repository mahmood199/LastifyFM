package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class Albums(
    @SerializedName("@attr")
    val attr: OffsetAttribute,
    @SerializedName("album")
    val album: List<Album>
)