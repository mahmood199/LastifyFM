package com.example.androidapplicationtemplate.ui.genreDetails.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.Album
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.domain.usecase.GetTopAlbumsByTagUseCase
import com.example.androidapplicationtemplate.ui.genreDetails.effect.AlbumsEffect
import com.example.androidapplicationtemplate.ui.genreDetails.intent.AlbumsIntent
import com.example.androidapplicationtemplate.ui.genreDetails.state.AlbumsState
import com.example.androidapplicationtemplate.ui.genreDetails.state.GenreDetailState
import com.example.androidapplicationtemplate.util.BundleKeyIdentifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val getTopAlbumsByTagUseCase: GetTopAlbumsByTagUseCase,
) : ViewModel() {

    val intents: Channel<AlbumsIntent> =
        Channel(Channel.UNLIMITED)

    private val _state = MutableStateFlow<AlbumsState>(AlbumsState.Idle)
    val state: StateFlow<AlbumsState>
        get() = _state

    private val _effect = Channel<AlbumsEffect>()
    val effect: Flow<AlbumsEffect>
        get() = _effect.receiveAsFlow()

    private lateinit var tag : Tag

    init {
        receiveIntents()
    }

    private fun receiveIntents() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect {
                when(it) {
                    is AlbumsIntent.GetTagInfo -> {

                    }
                    is AlbumsIntent.GetTopAlbumsByTag -> {
                        getTopAlbums(tag)
                    }
                    is AlbumsIntent.GetTopArtistsByTag -> {

                    }
                    is AlbumsIntent.GetTopTracksByTag -> {

                    }
                    is AlbumsIntent.GetArgs -> {
                        processArgs(it.arguments)
                    }
                }
            }
        }
    }

    private fun processArgs(arguments: Bundle?) {
        tag = arguments?.getParcelable(BundleKeyIdentifier.TAG) ?: Tag(name = "")
        _state.value = AlbumsState.ArgumentsProcessed(tag)
    }

    private fun getTopAlbums(tag: Tag) {
        viewModelScope.launch {
            getTopAlbumsByTagUseCase(tag).collect {
                when(it) {
                    is Resource.Failure -> {
                        _state.value = AlbumsState.Error(it.failureStatus, "")
                    }
                    is Resource.Success -> {
                        _state.value = AlbumsState.ShowAlbumResult(it.value)
                    } else -> {}
                }
            }
        }
    }


}