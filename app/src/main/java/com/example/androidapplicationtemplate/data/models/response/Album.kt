package com.example.androidapplicationtemplate.data.models.response

import android.os.Parcelable
import com.example.androidapplicationtemplate.ui.genreDetails.adapter.AdapterItems
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    @SerializedName("@attr")
    val rank: Rank = Rank(),
    @SerializedName("artist")
    val artist: Artist = Artist(),
    @SerializedName("image")
    val images: List<Image> = listOf(),
    @SerializedName("mbid")
    val mbid: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String = "",
) : Parcelable, AdapterItems