package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class TopArtists(
    @SerializedName("@attr")
    val attr: OffsetAttribute,
    @SerializedName("artist")
    val artist: List<Artist>
)