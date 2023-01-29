package com.example.androidapplicationtemplate.ui.artist

import com.example.androidapplicationtemplate.data.models.response.Artist
import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class ArtistInfoState {
    object Idle : ArtistInfoState()
    object Loading : ArtistInfoState()
    object Error : ArtistInfoState()
    data class ResponseReceived(val tags: Artist) : ArtistInfoState()
    data class ShowMoreTags(val tags: MutableList<Tag>) : ArtistInfoState()
}