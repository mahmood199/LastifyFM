package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("@attr")
    val rank: Rank,
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("image")
    val images: List<Image>,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)