package com.example.androidapplicationtemplate.ui.genreDetails.effect

sealed class ArtistsEffect{
    data class NavigateToArtistDetailScreen(val name: String) : ArtistsEffect()
    object Effect1 : ArtistsEffect()
}
