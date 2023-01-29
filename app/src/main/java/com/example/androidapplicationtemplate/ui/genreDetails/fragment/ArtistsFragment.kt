package com.example.androidapplicationtemplate.ui.genreDetails.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidapplicationtemplate.R
import com.example.androidapplicationtemplate.data.models.response.Artist
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.databinding.FragmentArtistsBinding
import com.example.androidapplicationtemplate.ui.artist.ArtistInfoActivity
import com.example.androidapplicationtemplate.ui.genreDetails.adapter.GenericAdapter
import com.example.androidapplicationtemplate.ui.genreDetails.adapter.RecyclerViewItemClickAction
import com.example.androidapplicationtemplate.ui.genreDetails.effect.ArtistsEffect
import com.example.androidapplicationtemplate.ui.genreDetails.intent.ArtistsIntent
import com.example.androidapplicationtemplate.ui.genreDetails.state.ArtistsState
import com.example.androidapplicationtemplate.ui.genreDetails.viewmodel.ArtistsViewModel
import com.example.androidapplicationtemplate.util.BundleKeyIdentifier
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtistsFragment : Fragment() {

    private lateinit var binding: FragmentArtistsBinding
    private val viewModel by viewModels<ArtistsViewModel>()

    companion object {
        var POSITION_ARG = BundleKeyIdentifier.POSITION_ARG
        var TAG = BundleKeyIdentifier.TAG

        fun newInstance(
            position: Int,
            tag: Tag,
        ) = ArtistsFragment().also { fragment ->
            Bundle().apply {
                putInt(POSITION_ARG, position)
                putParcelable(TAG, tag)
                fragment.arguments = this
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentArtistsBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setObservers()
        getArgs()
    }

    private fun getArgs() {
        triggerAction(ArtistsIntent.GetArgs(arguments))
    }

    private fun setViews() {
        binding.apply {
            rvArtists.recycledViewPool.setMaxRecycledViews(R.layout.layout_item_artists, 5)
            rvArtists.adapter = GenericAdapter {
                handleItemClickListener(it)
            }
        }
    }

    private fun handleItemClickListener(it: RecyclerViewItemClickAction) {
        when (it) {
            is RecyclerViewItemClickAction.ArtistClicked -> {
                Log.d("ArtistsFragment1", it.artist.toString())
                triggerAction(ArtistsIntent.RedirectToArtistDetailScreen(it.artist.name))
            }
            else -> {}
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

    private fun setUIState(it: ArtistsState) {
        when (it) {
            is ArtistsState.Error -> {}
            ArtistsState.Idle -> {}
            ArtistsState.Loading -> {}
            ArtistsState.State1 -> {}
            is ArtistsState.ArgumentsProcessed -> {
                triggerAction(ArtistsIntent.GetTopArtistsByTag)
            }
            is ArtistsState.ShowArtistResult -> {
                addItemsToRecyclerView(it.value.topArtists.artist)
            }
        }
    }

    private fun addItemsToRecyclerView(artists: List<Artist>) {
        binding.apply {
            (rvArtists.adapter as GenericAdapter).addItems(artists)
        }
    }


    private fun setUIEffect(it: ArtistsEffect) {
        when (it) {
            ArtistsEffect.Effect1 -> {}
            is ArtistsEffect.NavigateToArtistDetailScreen -> {
                Log.d("ArtistsFragment2", it.name)
                startActivity(Intent(context, ArtistInfoActivity::class.java).putExtra(
                    BundleKeyIdentifier.ARTIST, it.name))
            }
        }
    }

    private fun triggerAction(it: ArtistsIntent) {
        lifecycleScope.launch {
            viewModel.intents.send(it)
        }
    }

}