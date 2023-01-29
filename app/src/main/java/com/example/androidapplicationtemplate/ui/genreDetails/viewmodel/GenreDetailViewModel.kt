package com.example.androidapplicationtemplate.ui.genreDetails.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.domain.usecase.GetTagInfoUseCase
import com.example.androidapplicationtemplate.domain.usecase.GetTopAlbumsByTagUseCase
import com.example.androidapplicationtemplate.domain.usecase.GetTopArtistsByTagUseCase
import com.example.androidapplicationtemplate.domain.usecase.GetTopTracksByTagUseCase
import com.example.androidapplicationtemplate.ui.genreDetails.effect.GenreDetailEffect
import com.example.androidapplicationtemplate.ui.genreDetails.intent.GenreDetailIntent
import com.example.androidapplicationtemplate.ui.genreDetails.state.GenreDetailState
import com.example.androidapplicationtemplate.util.BundleKeyIdentifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreDetailViewModel @Inject constructor(
    private val getTagInfoUseCase: GetTagInfoUseCase,
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

    private lateinit var tag : Tag

    init {
        receiveIntents()
    }

    private fun receiveIntents() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect {
                when(it) {
                    is GenreDetailIntent.GetTagInfo -> {
                        getTagInfo(tag)
                    }
                    is GenreDetailIntent.GetTopAlbumsByTag -> {
                    }
                    is GenreDetailIntent.GetTopArtistsByTag -> {
                        getTopArtists(tag)
                    }
                    is GenreDetailIntent.GetTopTracksByTag -> {
                        getTopTracks(tag)

                    }
                    is GenreDetailIntent.ParseArgs -> {
                        getTagParams(it.intent)
                    }
                }
            }
        }
    }

    private fun getTagParams(intent: Intent?) {
        tag = intent?.extras?.getParcelable(BundleKeyIdentifier.TAG) ?: Tag(name = "")
        _state.value = GenreDetailState.ArgumentsParsed(tag)
    }

    private fun getTagInfo(tag: Tag) {
        viewModelScope.launch {
            getTagInfoUseCase(tag.name).collect {
                when(it) {
                    is Resource.Failure -> {
                        _state.value = GenreDetailState.Error(it.failureStatus, "")
                    }
                    is Resource.Success -> {
                        this@GenreDetailViewModel.tag = it.value.tag
                        _state.value = GenreDetailState.SetTagDescription(it.value.tag)
                    }
                    else -> {}
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