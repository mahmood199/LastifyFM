package com.example.androidapplicationtemplate.data.models.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artist(
    @SerializedName("mbid")
    val mbid: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("@attr")
    val rank: Rank = Rank(),
    @SerializedName("image")
    val image: List<Image> = listOf(),
    @SerializedName("streamable")
    val streamable: String = "",
) : Parcelable