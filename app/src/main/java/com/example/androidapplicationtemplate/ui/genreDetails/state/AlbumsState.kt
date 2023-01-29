package com.example.androidapplicationtemplate.ui.genreDetails.state

import com.example.androidapplicationtemplate.core.network.FailureStatus
import com.example.androidapplicationtemplate.data.models.response.AlbumListResponse
import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class AlbumsState {
    object Idle : AlbumsState()
    object Loading : AlbumsState()
    data class Error(val failureStatus: FailureStatus, val message: String) : AlbumsState()
    object State1 : AlbumsState()
    data class ArgumentsProcessed(val tag: Tag) : AlbumsState()
    data class ShowAlbumResult(val value: AlbumListResponse) : AlbumsState()
}
