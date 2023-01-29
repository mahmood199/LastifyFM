package com.example.androidapplicationtemplate.ui.genreDetails.state

import com.example.androidapplicationtemplate.core.network.FailureStatus
import com.example.androidapplicationtemplate.data.models.response.ArtistListResponse
import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class ArtistsState{
    object Idle : ArtistsState()
    object Loading : ArtistsState()
    data class Error(val failureStatus: FailureStatus, val message: String) : ArtistsState()
    object State1 : ArtistsState()
    data class ArgumentsProcessed(val tag: Tag) : ArtistsState()
    data class ShowArtistResult(val value: ArtistListResponse) : ArtistsState()
}
