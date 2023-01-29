package com.example.androidapplicationtemplate.ui.genre

import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class GenreEffect {
    data class NavigateToGenreDetailScreen(val tag: Tag, val index: Int) : GenreEffect()
}