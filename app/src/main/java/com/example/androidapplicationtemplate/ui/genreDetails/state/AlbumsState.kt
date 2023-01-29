package com.example.androidapplicationtemplate.ui.genreDetails.state

import com.example.androidapplicationtemplate.data.models.response.Album

sealed class AlbumsState{
    object Idle : AlbumsState()
    object Loading : AlbumsState()
    object Error : AlbumsState()
    object State1 : AlbumsState()
    data class ArgumentsProcessed(val album: Album) : AlbumsState()
}
