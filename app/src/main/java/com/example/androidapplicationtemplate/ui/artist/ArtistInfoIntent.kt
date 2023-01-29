package com.example.androidapplicationtemplate.ui.artist

import android.content.Intent
import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class ArtistInfoIntent {
    object GetArtistDetails : ArtistInfoIntent()
    data class RedirectToGenreDetailScreen(val tag: Tag, val index: Int) : ArtistInfoIntent()
    data class GetArgs(val intent: Intent) : ArtistInfoIntent()
}