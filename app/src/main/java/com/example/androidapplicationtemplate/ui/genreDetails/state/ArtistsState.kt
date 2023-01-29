package com.example.androidapplicationtemplate.ui.genreDetails.state

import com.example.androidapplicationtemplate.data.models.response.Album

sealed class ArtistsState{
    object Idle : ArtistsState()
    object Loading : ArtistsState()
    object Error : ArtistsState()
    object State1 : ArtistsState()
    data class ArgumentsProcessed(val album: Album) : ArtistsState()
}
