package com.example.androidapplicationtemplate.ui.genreDetails.intent

import android.os.Bundle
import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class AlbumsIntent {
    data class GetTagInfo(val tag: Tag) : AlbumsIntent()
    object GetTopAlbumsByTag : AlbumsIntent()
    data class GetTopArtistsByTag(val tag: Tag) : AlbumsIntent()
    data class GetTopTracksByTag(val tag: Tag) : AlbumsIntent()
    data class GetArgs(val arguments: Bundle?) : AlbumsIntent()
}
