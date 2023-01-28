package com.example.androidapplicationtemplate.ui.tag_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.domain.usecase.GetTagsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TagsViewModel @Inject constructor(
	private val getTagsUseCase: GetTagsUseCase
) : ViewModel() {

	companion object {
		const val INITIAL_ITEMS_TO_BE_SHOWN = 10
	}

	val intents: Channel<TagIntent> =
		Channel(Channel.UNLIMITED)

	private val _state = MutableStateFlow<TagState>(TagState.Idle)
	val state: StateFlow<TagState>
		get() = _state

	private val _effect = Channel<TagEffect>()
	val effect: Flow<TagEffect>
		get() = _effect.receiveAsFlow()

	private val tags = mutableListOf<Tag>()
	init {
	    receiveIntents()
	}


	private fun receiveIntents() {
		viewModelScope.launch {
			intents.consumeAsFlow().collect {
				when(it) {
					TagIntent.GetTags -> doOperation1()
					TagIntent.ShowMoreTags -> {
						showMoreTags()
					}
					is TagIntent.RedirectToGenreDetailScreen -> navigateToGenreDetailScreen(it.tag, it.index)
				}
			}
		}
	}

	private fun navigateToGenreDetailScreen(tag: Tag, index: Int) {
		viewModelScope.launch {
			_effect.send(TagEffect.NavigateToGenreDetailScreen(tag, index))
		}
	}

	private fun showMoreTags() {
		_state.value = TagState.ShowMoreTags(tags.subList(INITIAL_ITEMS_TO_BE_SHOWN, tags.size - 1))
	}

	private fun doOperation1() {
		viewModelScope.launch {
			getTagsUseCase.invoke().collect {
				when(it) {
					Resource.Default -> {}
					is Resource.Failure -> {
						_state.value = TagState.Error
					}
					Resource.Loading -> {
						_state.value = TagState.Loading
					}
					is Resource.Success -> {
						tags.clear()
						tags.addAll(it.value.topTags.tags)
						_state.value = TagState.ResponseReceived(it.value.topTags.tags.take(INITIAL_ITEMS_TO_BE_SHOWN))
					}
				}
			}
		}
	}


}