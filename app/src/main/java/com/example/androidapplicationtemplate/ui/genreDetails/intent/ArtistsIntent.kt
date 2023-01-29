package com.example.androidapplicationtemplate.ui.genreDetails.intent

import android.os.Bundle
import com.example.androidapplicationtemplate.data.models.response.Tag

sealed class ArtistsIntent {
    data class GetTagInfo(val tag: Tag) : ArtistsIntent()
    object GetTopAlbumsByTag : ArtistsIntent()
    data class GetTopArtistsByTag(val tag: Tag) : ArtistsIntent()
    data class GetTopTracksByTag(val tag: Tag) : ArtistsIntent()
    data class GetArgs(val arguments: Bundle?) : ArtistsIntent()
}
