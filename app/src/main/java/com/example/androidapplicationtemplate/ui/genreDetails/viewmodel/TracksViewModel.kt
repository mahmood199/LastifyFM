package com.example.androidapplicationtemplate.ui.genreDetails.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.domain.usecase.GetTopTracksByTagUseCase
import com.example.androidapplicationtemplate.ui.genreDetails.effect.TracksEffect
import com.example.androidapplicationtemplate.ui.genreDetails.intent.TracksIntent
import com.example.androidapplicationtemplate.ui.genreDetails.state.TracksState
import com.example.androidapplicationtemplate.util.BundleKeyIdentifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TracksViewModel @Inject constructor(
    private val getTopTracksByTagUseCase: GetTopTracksByTagUseCase
) : ViewModel() {

    val intents: Channel<TracksIntent> =
        Channel(Channel.UNLIMITED)

    private val _state = MutableStateFlow<TracksState>(TracksState.Idle)
    val state: StateFlow<TracksState>
        get() = _state

    private val _effect = Channel<TracksEffect>()
    val effect: Flow<TracksEffect>
        get() = _effect.receiveAsFlow()

    private lateinit var tag : Tag

    init {
        receiveIntents()
    }

    private fun receiveIntents() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect {
                when(it) {
                    is TracksIntent.GetTopTracksByTag -> {
                        getTopTracks(tag)
                    }
                    is TracksIntent.GetArgs -> {
                        processArgs(it.arguments)
                    }
                }
            }
        }
    }

    private fun processArgs(arguments: Bundle?) {
        tag = arguments?.getParcelable(BundleKeyIdentifier.TAG) ?: Tag(name = "")
        _state.value = TracksState.ArgumentsProcessed(tag)
    }

    private fun getTopTracks(tag: Tag) {
        viewModelScope.launch {
            getTopTracksByTagUseCase(tag).collect {
                when(it) {
                    is Resource.Failure -> {
                        _state.value = TracksState.Error(it.failureStatus, "")
                    }
                    is Resource.Success -> {
                        _state.value = TracksState.ShowArtistResult(it.value)
                    } else -> {}
                }
            }
        }
    }



}