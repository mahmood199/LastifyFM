package com.example.androidapplicationtemplate.data.models.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Links(
    val link: Link,
) : Parcelable