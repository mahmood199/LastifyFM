package com.example.androidapplicationtemplate.data.models.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stats(
    val listeners: String,
    val playcount: String,
) : Parcelable