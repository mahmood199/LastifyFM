package com.example.androidapplicationtemplate.ui.genre

import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class GenreIntent {
    object GetTags : GenreIntent()
    object ShowMoreTags : GenreIntent()
    data class RedirectToGenreDetailScreen(val tag: Tag, val index: Int) : GenreIntent()
}