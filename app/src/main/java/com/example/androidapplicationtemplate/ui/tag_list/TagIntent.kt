package com.example.androidapplicationtemplate.ui.tag_list

import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class TagIntent {
    object GetTags : TagIntent()
    object ShowMoreTags : TagIntent()
    data class RedirectToGenreDetailScreen(val tag: Tag, val index: Int) : TagIntent()
}