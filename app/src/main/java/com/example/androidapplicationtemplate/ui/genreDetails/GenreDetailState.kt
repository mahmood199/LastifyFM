package com.example.androidapplicationtemplate.ui.genreDetails

sealed class GenreDetailState{
    object Idle : GenreDetailState()
    object Loading : GenreDetailState()
    object Error : GenreDetailState()
    object State1 : GenreDetailState()
}
