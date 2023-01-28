package com.example.androidapplicationtemplate.ui.tag_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapplicationtemplate.core.network.Resource
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

	init {
	    receiveIntents()
	}

	val intents: Channel<TagIntent> =
		Channel(Channel.UNLIMITED)

	private val _state = MutableStateFlow<TagState>(TagState.Idle)
	val state: StateFlow<TagState>
		get() = _state

	private val _effect = Channel<TagEffect>()
	val effect: Flow<TagEffect>
		get() = _effect.receiveAsFlow()


	private fun receiveIntents() {
		viewModelScope.launch {
			intents.consumeAsFlow().collect {
				when(it) {
					TagIntent.GetTags -> doOperation1()
					TagIntent.Intent2 -> doOperation2()
					TagIntent.Intent3 -> doOperation3()
					TagIntent.Intent4 -> doOperation4()
				}
			}
		}
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
						_state.value = TagState.ResponseReceived(it.value)
					}
				}
			}
		}
	}

	private fun doOperation2() {
		TODO("Not yet implemented")
	}

	private fun doOperation3() {
		TODO("Not yet implemented")
	}

	private fun doOperation4() {
		TODO("Not yet implemented")
	}


}