package com.example.androidapplicationtemplate.ui.artist

import com.example.androidapplicationtemplate.data.models.response.Artist

sealed class ArtistInfoState {
    object Idle : ArtistInfoState()
    object Loading : ArtistInfoState()
    object Error : ArtistInfoState()
    object ArgumentsParsed : ArtistInfoState()
    data class SetArtistDetails(val artist: Artist) : ArtistInfoState()
}