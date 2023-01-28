package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class TrackListResponse(
    @SerializedName("tracks")
    val tracks: Tracks
)