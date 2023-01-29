package com.example.androidapplicationtemplate.ui.genreDetails.intent

import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class AlbumsIntent {
    data class GetTagInfo(val tag: Tag) : AlbumsIntent()
    data class GetTopAlbumsByTag(val tag: Tag) : AlbumsIntent()
    data class GetTopArtistsByTag(val tag: Tag) : AlbumsIntent()
    data class GetTopTracksByTag(val tag: Tag) : AlbumsIntent()
}
