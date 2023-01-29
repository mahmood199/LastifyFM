package com.example.androidapplicationtemplate.data.models.response

import android.os.Parcelable
import com.example.androidapplicationtemplate.ui.genreDetails.adapter.AdapterItems
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable, AdapterItems