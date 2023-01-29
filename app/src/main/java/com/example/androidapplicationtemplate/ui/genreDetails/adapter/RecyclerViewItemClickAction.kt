package com.example.androidapplicationtemplate.ui.genreDetails.adapter

import com.example.androidapplicationtemplate.data.models.response.Album
import com.example.androidapplicationtemplate.data.models.response.Artist
import com.example.androidapplicationtemplate.data.models.response.Track

sealed class RecyclerViewItemClickAction {
    data class AlbumClicked(val album: Album) : RecyclerViewItemClickAction()
    data class ArtistClicked(val artist: Artist) : RecyclerViewItemClickAction()
    data class TrackClicked(val track: Track) : RecyclerViewItemClickAction()
}