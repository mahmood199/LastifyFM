package com.example.androidapplicationtemplate.ui.genreDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.domain.usecase.GetTopAlbumsByTagUseCase
import com.example.androidapplicationtemplate.domain.usecase.GetTopArtistsByTagUseCase
import com.example.androidapplicationtemplate.domain.usecase.GetTopTracksByTagUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreDetailViewModel @Inject constructor(
    private val getTopAlbumsByTagUseCase: GetTopAlbumsByTagUseCase,
    private val getTopArtistsByTagUseCase: GetTopArtistsByTagUseCase,
    private val getTopTracksByTagUseCase: GetTopTracksByTagUseCase,
) : ViewModel() {

    val intents: Channel<GenreDetailIntent> =
        Channel(Channel.UNLIMITED)

    private val _state = MutableStateFlow<GenreDetailState>(GenreDetailState.Idle)
    val state: StateFlow<GenreDetailState>
        get() = _state

    private val _effect = Channel<GenreDetailEffect>()
    val effect: Flow<GenreDetailEffect>
        get() = _effect.receiveAsFlow()

    private val tags = mutableListOf<Tag>()

    init {
        receiveIntents()
    }

    private fun receiveIntents() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect {
                when(it) {
                    is GenreDetailIntent.GetTopAlbumsByTag -> {
                        getTopAlbums(it.tag)
                    }
                    is GenreDetailIntent.GetTopArtistsByTag -> {
                        getTopArtists(it.tag)
                    }
                    is GenreDetailIntent.GetTopTracksByTag -> {
                        getTopTracks(it.tag)

                    }
                }
            }
        }
    }


    private fun getTopAlbums(tag: Tag) {
        viewModelScope.launch {
            getTopAlbumsByTagUseCase(tag).collect {
                when(it) {
                    Resource.Default -> {

                    }
                    is Resource.Failure -> {

                    }
                    Resource.Loading -> {

                    }
                    is Resource.Success -> {

                    }
                }
            }
        }
    }

    private fun getTopArtists(tag: Tag) {
        viewModelScope.launch {
            getTopArtistsByTagUseCase(tag).collect {
                when(it) {
                    Resource.Default -> {

                    }
                    is Resource.Failure -> {

                    }
                    Resource.Loading -> {

                    }
                    is Resource.Success -> {

                    }
                }
            }
        }
    }

    private fun getTopTracks(tag: Tag) {
        viewModelScope.launch {
            getTopTracksByTagUseCase(tag).collect {
                when(it) {
                    Resource.Default -> {

                    }
                    is Resource.Failure -> {

                    }
                    Resource.Loading -> {

                    }
                    is Resource.Success -> {

                    }
                }
            }
        }
    }

}