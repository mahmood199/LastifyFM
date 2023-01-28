package com.example.androidapplicationtemplate.data.models.response

import com.google.gson.annotations.SerializedName

data class TopTags(
    @SerializedName("@attr")
    val offsetAttribute: OffsetAttribute,
    @SerializedName("tag")
    val tags: List<Tag>
)