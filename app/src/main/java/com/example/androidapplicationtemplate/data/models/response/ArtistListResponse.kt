package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class ArtistListResponse(
    @SerializedName("topartists")
    val topArtists: TopArtists
)