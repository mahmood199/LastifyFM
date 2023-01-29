package com.example.androidapplicationtemplate.ui.genre

import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class GenreState {
    object Idle : GenreState()
    object Loading : GenreState()
    object Error : GenreState()
    data class ResponseReceived(val tags: List<Tag>) : GenreState()
    data class ShowMoreTags(val tags: MutableList<Tag>) : GenreState()
}