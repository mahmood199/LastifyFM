package com.example.androidapplicationtemplate.ui.genre

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.androidapplicationtemplate.R
import com.example.androidapplicationtemplate.core.extension.makeGone
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.databinding.ActivityTagsBinding
import com.example.androidapplicationtemplate.ui.genreDetails.GenreDetailActivity
import com.example.androidapplicationtemplate.util.BundleKeyIdentifier
import com.example.androidapplicationtemplate.util.SnackBarBuilder
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GenresActivity : AppCompatActivity() {

	private lateinit var binding : ActivityTagsBinding
	private val viewModel : GenreViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setViews()
		setObservers()
		triggerAction(GenreIntent.GetTags)
	}

	private fun setViews() {
		binding = ActivityTagsBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setClickListeners()
	}

	private fun setClickListeners() {
		binding.apply {
			tvSeeMoreTags.setOnClickListener {
				triggerAction(GenreIntent.ShowMoreTags)
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

	private fun setUIState(it: GenreState) {
		when(it) {
			GenreState.Idle -> {}
			GenreState.Loading -> {}
			is GenreState.ResponseReceived -> {
				addChipsToChipGroup(it.tags)
			}
			GenreState.Error -> {
				showError()
			}
			is GenreState.ShowMoreTags -> {
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
            setTextColor(ContextCompat.getColor(this@GenresActivity, R.color.white))
			setOnClickListener { _ ->
				showGenreDetail(it, index)
			}
			tag = it.count
			setOnCloseIconClickListener {
				binding.cgTags.removeViewAt(index)
			}
        }
	}

	private fun showGenreDetail(it: Tag, index: Int) {
		triggerAction(GenreIntent.RedirectToGenreDetailScreen(it, index))
	}

	private fun showError() {
		SnackBarBuilder.getSnackbar(this, "Failed", Snackbar.LENGTH_SHORT).show()
	}

	private fun setUIEffect(it: GenreEffect) {
		when(it) {
			is GenreEffect.NavigateToGenreDetailScreen -> {
				startActivity(
                    Intent(this, GenreDetailActivity::class.java)
                        .putExtra(BundleKeyIdentifier.TAG, it.tag))
			}
		}
	}

	private fun triggerAction(it: GenreIntent) {
		lifecycleScope.launch {
			viewModel.intents.send(it)
		}
	}

}