package com.example.androidapplicationtemplate.ui.artist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.androidapplicationtemplate.R
import com.example.androidapplicationtemplate.data.models.response.Artist
import com.example.androidapplicationtemplate.databinding.ActivityArtistInfoBinding
import com.example.androidapplicationtemplate.ui.genreDetails.GenreDetailActivity
import com.example.androidapplicationtemplate.ui.genreDetails.adapter.GenericAdapter
import com.example.androidapplicationtemplate.util.BundleKeyIdentifier
import com.example.androidapplicationtemplate.util.SnackBarBuilder
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
		getArgs()
	}

	private fun getArgs() {
		Log.d("ArtistInfoActivity", intent.extras?.getString(BundleKeyIdentifier.ARTIST) ?: "")
		triggerAction(ArtistInfoIntent.GetArgs(intent))
	}

	private fun setViews() {
		binding = ActivityArtistInfoBinding.inflate(layoutInflater)
		setContentView(binding.root)
		binding.rvTags.adapter = GenericAdapter {
			Toast.makeText(this, "Do nothing here", Toast.LENGTH_SHORT).show()
		}
		binding.rvTopTracks.adapter = GenericAdapter {
			Toast.makeText(this, "Do nothing here for Top tracks", Toast.LENGTH_SHORT).show()
		}
		binding.rvTopAlbums.adapter = GenericAdapter {
			Toast.makeText(this, "Do nothing here for Top albums", Toast.LENGTH_SHORT).show()
		}
		setClickListeners()
	}

	private fun setClickListeners() {
		binding.apply {

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
			ArtistInfoState.Error -> {
				showError()
			}
			ArtistInfoState.ArgumentsParsed -> {
				triggerAction(ArtistInfoIntent.GetArtistDetails)
			}
			is ArtistInfoState.SetArtistDetails -> {
				setArtistDetails(it.artist)
			}
		}
	}

	private fun setArtistDetails(artist: Artist) {
		binding.apply {
			tvTitle.text = artist.name
			tvPlaycount.text = artist.stats.playCount
			tvFollower.text = artist.stats.listeners

			Glide.with(root)
				.load(artist.image[2])
				.placeholder(ContextCompat.getDrawable(root.context, R.drawable.place_holder))
				.transition(DrawableTransitionOptions.withCrossFade(
					GenericAdapter.CROSS_FADE_ANIMATION_DURATION))
				.into(ivArtist)

			(rvTags.adapter as GenericAdapter).addItems(artist.tags.tag)
			(rvTopAlbums.adapter as GenericAdapter).addItems(artist.similar.artist)
			(rvTopTracks.adapter as GenericAdapter).addItems(artist.similar.artist)


		}
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