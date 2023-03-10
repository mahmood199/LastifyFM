package com.example.androidapplicationtemplate.data.models.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Link(
    @SerializedName("#text")
    val text: String = "",
    @SerializedName("href")
    val href: String = "",
    @SerializedName("rel")
    val rel: String = "",
) : Parcelable