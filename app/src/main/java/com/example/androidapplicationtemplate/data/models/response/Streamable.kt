package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class Streamable(
    @SerializedName("#text")
    val text: String,
    @SerializedName("fulltrack")
    val fullTrack: String
)