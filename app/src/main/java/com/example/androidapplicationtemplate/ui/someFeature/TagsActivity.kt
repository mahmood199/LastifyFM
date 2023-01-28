package com.example.androidapplicationtemplate.ui.someFeature

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.androidapplicationtemplate.databinding.ActivityTagsBinding
import com.example.androidapplicationtemplate.util.SnackBarBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TagsActivity : AppCompatActivity() {

	private lateinit var binding : ActivityTagsBinding
	private val viewModel : TagsViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityTagsBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setObservers()
		triggerAction(TagIntent.GetTags)
	}

	private fun setObservers() {
		lifecycleScope.launch {
			viewModel.state.collect {
				setUIState(it)
			}
		}

		lifecycleScope.launch {
			viewModel.effect.collect {
				setUIEffect(it)
			}
		}
	}

	private fun setUIState(it: TagState) {
		when(it) {
			TagState.Idle -> {}
			TagState.Loading -> {}
			is TagState.ResponseReceived -> {
				Toast.makeText(this, "${it.tagListResponse.topTags.tags.size}", Toast.LENGTH_SHORT).show()
				var text = ""
				it.tagListResponse.topTags.tags.forEach {
					text += "${it.name}			 ${it.count} 		${it.reach} \n"
				}
				binding.tvTags.text = text
			}
			TagState.Error -> {
				showError()
			}
			TagState.State4 -> TODO()
		}
	}

	private fun showError() {
		SnackBarBuilder.getSnackbar(this, "FAiled", Snackbar.LENGTH_SHORT).show()
	}

	private fun setUIEffect(it: TagEffect) {
		when(it) {
			TagEffect.Effect1 -> TODO()
			TagEffect.Effect2 -> TODO()
			TagEffect.Effect3 -> TODO()
			TagEffect.Effect4 -> TODO()
		}
	}

	private fun triggerAction(it: TagIntent) {
		lifecycleScope.launch {
			viewModel.intents.send(it)
		}
	}

}