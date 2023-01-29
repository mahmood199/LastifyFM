package com.example.androidapplicationtemplate.ui.genreDetails

import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class GenreDetailIntent{
    data class GetTopAlbumsByTag(val tag: Tag) : GenreDetailIntent()
    data class GetTopArtistsByTag(val tag: Tag) : GenreDetailIntent()
    data class GetTopTracksByTag(val tag: Tag) : GenreDetailIntent()
}
