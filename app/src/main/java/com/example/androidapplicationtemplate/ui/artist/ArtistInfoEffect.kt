package com.example.androidapplicationtemplate.ui.artist

import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class ArtistInfoEffect {
    data class NavigateToGenreDetailScreen(val tag: Tag, val index: Int) : ArtistInfoEffect()
}