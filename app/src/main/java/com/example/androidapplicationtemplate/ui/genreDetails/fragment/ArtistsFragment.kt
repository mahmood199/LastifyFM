package com.example.androidapplicationtemplate.ui.genreDetails.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidapplicationtemplate.data.models.response.Album
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.databinding.FragmentAlbumsBinding
import com.example.androidapplicationtemplate.ui.genreDetails.effect.AlbumsEffect
import com.example.androidapplicationtemplate.ui.genreDetails.effect.ArtistsEffect
import com.example.androidapplicationtemplate.ui.genreDetails.intent.AlbumsIntent
import com.example.androidapplicationtemplate.ui.genreDetails.intent.ArtistsIntent
import com.example.androidapplicationtemplate.ui.genreDetails.state.AlbumsState
import com.example.androidapplicationtemplate.ui.genreDetails.state.ArtistsState
import com.example.androidapplicationtemplate.ui.genreDetails.viewmodel.AlbumsViewModel
import com.example.androidapplicationtemplate.ui.genreDetails.viewmodel.ArtistsViewModel
import com.example.androidapplicationtemplate.util.BundleKeyIdentifier
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtistsFragment : Fragment() {

    private lateinit var binding: FragmentAlbumsBinding
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
        binding = FragmentAlbumsBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setObservers()
        getArgs()
        triggerAction(ArtistsIntent.GetTopAlbumsByTag)
    }

    private fun getArgs() {
        triggerAction(ArtistsIntent.GetArgs(arguments))
    }

    private fun setViews() {

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
                binding.tvAlbumText.text = it.tag.name
                triggerAction(ArtistsIntent.GetTopArtistsByTag)
            }
            is ArtistsState.ShowArtistResult -> {

            }
        }
    }

    private fun setUIEffect(it: ArtistsEffect) {
        when (it) {
            ArtistsEffect.Effect1 -> {}
        }
    }

    private fun triggerAction(it: ArtistsIntent) {
        lifecycleScope.launch {
            viewModel.intents.send(it)
        }
    }

}