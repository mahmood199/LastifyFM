package com.example.androidapplicationtemplate.ui.genreDetails.state

sealed class AlbumsState{
    object Idle : AlbumsState()
    object Loading : AlbumsState()
    object Error : AlbumsState()
    object State1 : AlbumsState()
}
