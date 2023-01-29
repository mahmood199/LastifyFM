package com.example.androidapplicationtemplate.ui.genreDetails.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.databinding.FragmentTracksBinding
import com.example.androidapplicationtemplate.ui.genreDetails.effect.TracksEffect
import com.example.androidapplicationtemplate.ui.genreDetails.intent.TracksIntent
import com.example.androidapplicationtemplate.ui.genreDetails.state.TracksState
import com.example.androidapplicationtemplate.ui.genreDetails.viewmodel.TracksViewModel
import com.example.androidapplicationtemplate.util.BundleKeyIdentifier
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TracksFragment : Fragment() {

    private lateinit var binding: FragmentTracksBinding
    private val viewModel by viewModels<TracksViewModel>()

    companion object {
        var POSITION_ARG = BundleKeyIdentifier.POSITION_ARG
        var TAG = BundleKeyIdentifier.TAG

        fun newInstance(
            position: Int,
            tag: Tag,
        ) = TracksFragment().also { fragment ->
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
        binding = FragmentTracksBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setObservers()
        getArgs()
        triggerAction(TracksIntent.GetTopTracksByTag)
    }

    private fun getArgs() {
        triggerAction(TracksIntent.GetArgs(arguments))
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

    private fun setUIState(it: TracksState) {
        when (it) {
            is TracksState.Error -> {}
            TracksState.Idle -> {}
            TracksState.Loading -> {}
            TracksState.State1 -> {}
            is TracksState.ArgumentsProcessed -> {
                binding.tvAlbumText.text = it.tag.name
                triggerAction(TracksIntent.GetTopArtistsByTag)
            }
            is TracksState.ShowArtistResult -> {

            }
        }
    }

    private fun setUIEffect(it: TracksEffect) {
        when (it) {
            TracksEffect.Effect1 -> {}
        }
    }

    private fun triggerAction(it: TracksIntent) {
        lifecycleScope.launch {
            viewModel.intents.send(it)
        }
    }

}