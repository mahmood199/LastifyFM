package com.example.androidapplicationtemplate.ui.genreDetails.state

import com.example.androidapplicationtemplate.core.network.FailureStatus
import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class GenreDetailState {
    object Idle : GenreDetailState()
    object Loading : GenreDetailState()
    data class Error(val failureStatus: FailureStatus, val message: String) : GenreDetailState()
    data class SetTagDescription(val tag: Tag) : GenreDetailState()
    object State1 : GenreDetailState()
    object ArgumentsParsed : GenreDetailState()
}
