package com.example.androidapplicationtemplate.ui.genreDetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplicationtemplate.domain.usecase.GetTopAlbumsByTagUseCase
import com.example.androidapplicationtemplate.ui.genreDetails.effect.AlbumsEffect
import com.example.androidapplicationtemplate.ui.genreDetails.intent.AlbumsIntent
import com.example.androidapplicationtemplate.ui.genreDetails.state.AlbumsState
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

                    }
                    is AlbumsIntent.GetTopArtistsByTag -> {

                    }
                    is AlbumsIntent.GetTopTracksByTag -> {

                    }
                }
            }
        }
    }


}