package com.example.androidapplicationtemplate.ui.genreDetails.intent

import android.content.Intent

sealed class GenreDetailIntent {
    object GetTagInfo : GenreDetailIntent()
    object GetTopAlbumsByTag : GenreDetailIntent()
    object GetTopArtistsByTag : GenreDetailIntent()
    object GetTopTracksByTag : GenreDetailIntent()
    data class ParseArgs(val intent: Intent?) : GenreDetailIntent()
}
