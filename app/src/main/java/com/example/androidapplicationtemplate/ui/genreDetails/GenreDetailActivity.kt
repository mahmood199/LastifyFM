package com.example.androidapplicationtemplate.ui.genreDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidapplicationtemplate.core.extension.makeGone
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.databinding.ActivityGenreDetailBinding
import com.example.androidapplicationtemplate.databinding.ActivityTagsBinding
import com.example.androidapplicationtemplate.ui.genres.GenreEffect
import com.example.androidapplicationtemplate.ui.genres.GenreIntent
import com.example.androidapplicationtemplate.ui.genres.GenreState
import com.example.androidapplicationtemplate.ui.genres.GenreViewModel
import com.example.androidapplicationtemplate.util.SnackBarBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GenreDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGenreDetailBinding
    private val viewModel : GenreDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViews()
        setObservers()
        getTagDetails()
    }

    private fun getTagDetails() {
        triggerAction(GenreDetailIntent.GetTagInfo(Tag(name = "disco")))
        triggerAction(GenreDetailIntent.GetTopAlbumsByTag(Tag(name = "disco")))
        triggerAction(GenreDetailIntent.GetTopArtistsByTag(Tag(name = "disco")))
        triggerAction(GenreDetailIntent.GetTopTracksByTag(Tag(name = "disco")))
    }

    private fun setViews() {
        binding = ActivityGenreDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

    private fun setUIState(it: GenreDetailState) {
        when(it) {
            GenreDetailState.Error -> {

            }
            GenreDetailState.Idle -> {

            }
            GenreDetailState.Loading -> {

            }
            GenreDetailState.State1 -> {

            }
        }
    }

    private fun setUIEffect(it: GenreDetailEffect) {
        when(it) {
            is GenreDetailEffect.Effect1 -> {
                SnackBarBuilder.getSnackbar(this, "OKAY", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun triggerAction(it: GenreDetailIntent) {
        lifecycleScope.launch {
            viewModel.intents.send(it)
        }
    }

}