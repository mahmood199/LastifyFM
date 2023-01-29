package com.example.androidapplicationtemplate.ui.artist

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.androidapplicationtemplate.R
import com.example.androidapplicationtemplate.core.extension.makeGone
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.databinding.ActivityArtistInfoBinding
import com.example.androidapplicationtemplate.databinding.ActivityTagsBinding
import com.example.androidapplicationtemplate.ui.genreDetails.GenreDetailActivity
import com.example.androidapplicationtemplate.util.BundleKeyIdentifier
import com.example.androidapplicationtemplate.util.SnackBarBuilder
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtistInfoActivity : AppCompatActivity() {

	private lateinit var binding : ActivityArtistInfoBinding
	private val viewModel : ArtistInfoViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setViews()
		setObservers()
		triggerAction(ArtistInfoIntent.GetTags)
	}

	private fun setViews() {
		binding = ActivityArtistInfoBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setClickListeners()
	}

	private fun setClickListeners() {
		binding.apply {
			tvSeeMoreTags.setOnClickListener {
				triggerAction(ArtistInfoIntent.ShowMoreTags)
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

	private fun setUIState(it: ArtistInfoState) {
		when(it) {
			ArtistInfoState.Idle -> {}
			ArtistInfoState.Loading -> {}
			is ArtistInfoState.ResponseReceived -> {
				addChipsToChipGroup(it.tags)
			}
			ArtistInfoState.Error -> {
				showError()
			}
			is ArtistInfoState.ShowMoreTags -> {
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
            setTextColor(ContextCompat.getColor(this@ArtistInfoActivity, R.color.white))
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
		triggerAction(ArtistInfoIntent.RedirectToGenreDetailScreen(it, index))
	}

	private fun showError() {
		SnackBarBuilder.getSnackbar(this, "Failed", Snackbar.LENGTH_SHORT).show()
	}

	private fun setUIEffect(it: ArtistInfoEffect) {
		when(it) {
			is ArtistInfoEffect.NavigateToGenreDetailScreen -> {
				startActivity(
                    Intent(this, GenreDetailActivity::class.java)
                        .putExtra(BundleKeyIdentifier.TAG, it.tag))
			}
		}
	}

	private fun triggerAction(it: ArtistInfoIntent) {
		lifecycleScope.launch {
			viewModel.intents.send(it)
		}
	}

}