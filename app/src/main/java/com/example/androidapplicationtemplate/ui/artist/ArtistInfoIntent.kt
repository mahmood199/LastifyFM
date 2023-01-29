package com.example.androidapplicationtemplate.ui.artist

import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class ArtistInfoIntent {
    object GetTags : ArtistInfoIntent()
    object ShowMoreTags : ArtistInfoIntent()
    data class RedirectToGenreDetailScreen(val tag: Tag, val index: Int) : ArtistInfoIntent()
}