package com.example.androidapplicationtemplate.ui.genres

import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class GenreState {
    object Idle : GenreState()
    object Loading : GenreState()
    data class ResponseReceived(val tags: List<Tag>) : GenreState()
    data class ShowMoreTags(val tags: MutableList<Tag>) : GenreState()
    object Error : GenreState()
}