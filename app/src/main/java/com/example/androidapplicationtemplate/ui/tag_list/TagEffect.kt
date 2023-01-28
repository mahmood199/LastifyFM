package com.example.androidapplicationtemplate.ui.tag_list

import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class TagEffect {
    data class NavigateToGenreDetailScreen(val tag: Tag, val index: Int) : TagEffect()
}