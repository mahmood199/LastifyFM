package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("@attr")
    val offsetAttribute: OffsetAttribute,
    @SerializedName("track")
    val track: List<Track>
)