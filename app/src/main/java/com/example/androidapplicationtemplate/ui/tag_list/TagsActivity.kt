package com.example.androidapplicationtemplate.ui.tag_list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.androidapplicationtemplate.R
import com.example.androidapplicationtemplate.core.extension.makeGone
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.databinding.ActivityTagsBinding
import com.example.androidapplicationtemplate.util.SnackBarBuilder
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TagsActivity : AppCompatActivity() {

	private lateinit var binding : ActivityTagsBinding
	private val viewModel : TagsViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setViews()
		setObservers()
		triggerAction(TagIntent.GetTags)
	}

	private fun setViews() {
		binding = ActivityTagsBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setClickListeners()
	}

	private fun setClickListeners() {
		binding.apply {
			tvSeeMoreTags.setOnClickListener {
				triggerAction(TagIntent.ShowMoreTags)
			}
		}
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
				addChipsToChipGroup(it.tags)
			}
			TagState.Error -> {
				showError()
			}
			is TagState.ShowMoreTags -> {
				binding.tvSeeMoreTags.makeGone()
				addChipsToChipGroup(it.tags)
			}
		}
	}

	private fun addChipsToChipGroup(tags: List<Tag>) {
		binding.apply {
			tags.forEachIndexed { index, tag ->
				val chip = getCustomChip(tag, index)
                cgTags.addView(chip)
			}
		}
	}

	private fun getCustomChip(it: Tag, index: Int): Chip {
		return Chip(this).apply {
            text = it.name
            isCloseIconVisible = false
            setChipBackgroundColorResource(R.color.teal_200)
            setTextColor(ContextCompat.getColor(this@TagsActivity, R.color.white))
			setOnClickListener { _ ->
				showText(it, index)
			}
			tag = it.count
			setOnCloseIconClickListener {
				binding.cgTags.removeViewAt(index)
			}
        }
	}

	private fun showText(it: Tag, index: Int) {
		triggerAction(TagIntent.RedirectToGenreDetailScreen(it, index))
	}

	private fun showError() {
		SnackBarBuilder.getSnackbar(this, "Failed", Snackbar.LENGTH_SHORT).show()
	}

	private fun setUIEffect(it: TagEffect) {
		when(it) {
			is TagEffect.NavigateToGenreDetailScreen -> {
				SnackBarBuilder.getSnackbar(this, "${it.tag} ${it.index}", Snackbar.LENGTH_SHORT).show()
			}
		}
	}

	private fun triggerAction(it: TagIntent) {
		lifecycleScope.launch {
			viewModel.intents.send(it)
		}
	}

}