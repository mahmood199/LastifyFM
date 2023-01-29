package com.example.androidapplicationtemplate.data.models.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tags(
    @SerializedName("tags")
    val tag: List<Tag> = listOf(),
) : Parcelable