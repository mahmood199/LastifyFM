package com.example.androidapplicationtemplate.data.models.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bio(
    @SerializedName("content")
    val content: String = "",
    @SerializedName("links")
    val links: Links = Links(),
    @SerializedName("summary")
    val summary: String = ""
) : Parcelable