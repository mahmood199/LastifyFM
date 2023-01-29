package com.example.androidapplicationtemplate.ui.artist

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.Artist
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.domain.usecase.GetArtistInfoUseCase
import com.example.androidapplicationtemplate.util.BundleKeyIdentifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistInfoViewModel @Inject constructor(
	private val getArtistInfoUseCase: GetArtistInfoUseCase
) : ViewModel() {

	companion object {
		const val INITIAL_ITEMS_TO_BE_SHOWN = 10
	}

	val intents: Channel<ArtistInfoIntent> = Channel(Channel.UNLIMITED)

	private val _state = MutableStateFlow<ArtistInfoState>(ArtistInfoState.Idle)
	val state: StateFlow<ArtistInfoState>
		get() = _state

	private val _effect = Channel<ArtistInfoEffect>()
	val effect: Flow<ArtistInfoEffect>
		get() = _effect.receiveAsFlow()

	private var artistName = ""

	init {
	    receiveIntents()
	}

	private fun receiveIntents() {
		viewModelScope.launch {
			intents.consumeAsFlow().collect {
				when(it) {
					ArtistInfoIntent.GetArtistDetails -> getArtistDetails()
					is ArtistInfoIntent.RedirectToGenreDetailScreen -> navigateToGenreDetailScreen(it.tag, it.index)
					is ArtistInfoIntent.GetArgs -> {

						processArgs(it.intent)
					}
				}
			}
		}
	}

	private fun processArgs(intent: Intent?) {
		artistName = intent?.extras?.getString(BundleKeyIdentifier.ARTIST) ?: ""
		_state.value = ArtistInfoState.ArgumentsParsed
	}

	private fun navigateToGenreDetailScreen(tag: Tag, index: Int) {
		viewModelScope.launch {
			_effect.send(ArtistInfoEffect.NavigateToGenreDetailScreen(tag, index))
		}
	}

	private fun getArtistDetails() {
		viewModelScope.launch {
			getArtistInfoUseCase.invoke(Artist()).collect {
				when(it) {
					Resource.Default -> {}
					is Resource.Failure -> {
						_state.value = ArtistInfoState.Error
					}
					Resource.Loading -> {
						_state.value = ArtistInfoState.Loading
					}
					is Resource.Success -> {
						_state.value = ArtistInfoState.SetArtistDetails(it.value.artist)
					}
				}
			}
		}
	}


}