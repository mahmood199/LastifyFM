package com.example.androidapplicationtemplate.data.models.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wiki(
    @SerializedName("summary")
    val summary: String,
    @SerializedName("content")
    val content: String,
) : Parcelable
