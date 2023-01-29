package com.example.androidapplicationtemplate.data.models.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bio(
    val content: String,
    val links: Links,
    val published: String,
    val summary: String
) : Parcelable