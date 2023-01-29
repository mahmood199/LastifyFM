package com.example.androidapplicationtemplate.ui.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.Artist
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.domain.usecase.GetArtistInfoUseCase
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

	private val tags = mutableListOf<Tag>()
	init {
	    receiveIntents()
	}


	private fun receiveIntents() {
		viewModelScope.launch {
			intents.consumeAsFlow().collect {
				when(it) {
					ArtistInfoIntent.GetTags -> doOperation1()
					ArtistInfoIntent.ShowMoreTags -> {
						showMoreTags()
					}
					is ArtistInfoIntent.RedirectToGenreDetailScreen -> navigateToGenreDetailScreen(it.tag, it.index)
				}
			}
		}
	}

	private fun navigateToGenreDetailScreen(tag: Tag, index: Int) {
		viewModelScope.launch {
			_effect.send(ArtistInfoEffect.NavigateToGenreDetailScreen(tag, index))
		}
	}

	private fun showMoreTags() {
		_state.value = ArtistInfoState.ShowMoreTags(tags.subList(INITIAL_ITEMS_TO_BE_SHOWN, tags.size - 1))
	}

	private fun doOperation1() {
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
						_state.value = ArtistInfoState.ResponseReceived(it.value.artist)
					}
				}
			}
		}
	}


}