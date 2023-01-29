package com.example.androidapplicationtemplate.data.models.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    @SerializedName("#text")
    val text: String,
    @SerializedName("size")
    val size: String
) : Parcelable