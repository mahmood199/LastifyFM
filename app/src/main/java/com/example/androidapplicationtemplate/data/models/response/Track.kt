package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("@attr")
    val rank: Rank,
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("image")
    val image: List<Image>,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("streamable")
    val streamable: Streamable,
    @SerializedName("url")
    val url: String,
)