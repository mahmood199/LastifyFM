package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class AlbumListResponse(
    @SerializedName("albums")
    val albums: Albums
)