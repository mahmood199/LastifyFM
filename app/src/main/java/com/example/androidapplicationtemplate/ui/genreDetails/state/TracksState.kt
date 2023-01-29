package com.example.androidapplicationtemplate.ui.genreDetails.state

import com.example.androidapplicationtemplate.core.network.FailureStatus
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.data.models.response.TrackListResponse

sealed class TracksState{
    object Idle : TracksState()
    object Loading : TracksState()
    data class Error(val failureStatus: FailureStatus, val message: String) : TracksState()
    object State1 : TracksState()
    data class ArgumentsProcessed(val tag: Tag) : TracksState()
    data class ShowArtistResult(val value: TrackListResponse) : TracksState()
}
