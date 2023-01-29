package com.example.androidapplicationtemplate.ui.genreDetails.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.Album
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.domain.usecase.GetTopAlbumsByTagUseCase
import com.example.androidapplicationtemplate.domain.usecase.GetTopArtistsByTagUseCase
import com.example.androidapplicationtemplate.ui.genreDetails.effect.AlbumsEffect
import com.example.androidapplicationtemplate.ui.genreDetails.effect.ArtistsEffect
import com.example.androidapplicationtemplate.ui.genreDetails.intent.AlbumsIntent
import com.example.androidapplicationtemplate.ui.genreDetails.intent.ArtistsIntent
import com.example.androidapplicationtemplate.ui.genreDetails.state.AlbumsState
import com.example.androidapplicationtemplate.ui.genreDetails.state.ArtistsState
import com.example.androidapplicationtemplate.util.BundleKeyIdentifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistsViewModel @Inject constructor(
    private val getTopArtistsByTagUseCase: GetTopArtistsByTagUseCase
) : ViewModel() {

    val intents: Channel<ArtistsIntent> =
        Channel(Channel.UNLIMITED)

    private val _state = MutableStateFlow<ArtistsState>(ArtistsState.Idle)
    val state: StateFlow<ArtistsState>
        get() = _state

    private val _effect = Channel<ArtistsEffect>()
    val effect: Flow<ArtistsEffect>
        get() = _effect.receiveAsFlow()

    private lateinit var tag : Tag

    init {
        receiveIntents()
    }

    private fun receiveIntents() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect {
                when(it) {
                    is ArtistsIntent.GetTopArtistsByTag -> {
                        getTopArtists(tag)
                    }
                    is ArtistsIntent.GetArgs -> {
                        processArgs(it.arguments)
                    }
                }
            }
        }
    }

    private fun processArgs(arguments: Bundle?) {
        tag = arguments?.getParcelable(BundleKeyIdentifier.TAG) ?: Tag(name = "")
        _state.value = ArtistsState.ArgumentsProcessed(tag)
    }

    private fun getTopArtists(tag: Tag) {
        viewModelScope.launch {
            getTopArtistsByTagUseCase(tag).collect {
                when(it) {
                    is Resource.Failure -> {
                        _state.value = ArtistsState.Error(it.failureStatus, "")
                    }
                    is Resource.Success -> {
                        _state.value = ArtistsState.ShowArtistResult(it.value)
                    } else -> {}
                }
            }
        }
    }



}