package com.example.androidapplicationtemplate.data.models.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Similar(
    @SerializedName("artist")
    val artist: List<Artist> = listOf(),
) : Parcelable