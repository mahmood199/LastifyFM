package com.example.androidapplicationtemplate.ui.genres

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.domain.usecase.GetAllTagsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
	private val getAllTagsUseCase: GetAllTagsUseCase
) : ViewModel() {

	companion object {
		const val INITIAL_ITEMS_TO_BE_SHOWN = 10
	}

	val intents: Channel<GenreIntent> =
		Channel(Channel.UNLIMITED)

	private val _state = MutableStateFlow<GenreState>(GenreState.Idle)
	val state: StateFlow<GenreState>
		get() = _state

	private val _effect = Channel<GenreEffect>()
	val effect: Flow<GenreEffect>
		get() = _effect.receiveAsFlow()

	private val tags = mutableListOf<Tag>()
	init {
	    receiveIntents()
	}


	private fun receiveIntents() {
		viewModelScope.launch {
			intents.consumeAsFlow().collect {
				when(it) {
					GenreIntent.GetTags -> doOperation1()
					GenreIntent.ShowMoreTags -> {
						showMoreTags()
					}
					is GenreIntent.RedirectToGenreDetailScreen -> navigateToGenreDetailScreen(it.tag, it.index)
				}
			}
		}
	}

	private fun navigateToGenreDetailScreen(tag: Tag, index: Int) {
		viewModelScope.launch {
			_effect.send(GenreEffect.NavigateToGenreDetailScreen(tag, index))
		}
	}

	private fun showMoreTags() {
		_state.value = GenreState.ShowMoreTags(tags.subList(INITIAL_ITEMS_TO_BE_SHOWN, tags.size - 1))
	}

	private fun doOperation1() {
		viewModelScope.launch {
			getAllTagsUseCase.invoke().collect {
				when(it) {
					Resource.Default -> {}
					is Resource.Failure -> {
						_state.value = GenreState.Error
					}
					Resource.Loading -> {
						_state.value = GenreState.Loading
					}
					is Resource.Success -> {
						tags.clear()
						tags.addAll(it.value.topTags.tags)
						_state.value = GenreState.ResponseReceived(it.value.topTags.tags.take(INITIAL_ITEMS_TO_BE_SHOWN))
					}
				}
			}
		}
	}


}