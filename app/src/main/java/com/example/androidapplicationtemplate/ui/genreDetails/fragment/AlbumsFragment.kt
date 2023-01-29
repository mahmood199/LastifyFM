package com.example.androidapplicationtemplate.ui.genreDetails.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidapplicationtemplate.data.models.response.Album
import com.example.androidapplicationtemplate.databinding.FragmentAlbumsBinding
import com.example.androidapplicationtemplate.ui.genreDetails.effect.AlbumsEffect
import com.example.androidapplicationtemplate.ui.genreDetails.intent.AlbumsIntent
import com.example.androidapplicationtemplate.ui.genreDetails.state.AlbumsState
import com.example.androidapplicationtemplate.ui.genreDetails.viewmodel.AlbumsViewModel
import com.example.androidapplicationtemplate.util.BundleKeyIdentifier
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumsFragment : Fragment() {

    private lateinit var binding: FragmentAlbumsBinding
    private val viewModel by viewModels<AlbumsViewModel>()

    companion object {
        var POSITION_ARG = BundleKeyIdentifier.POSITION_ARG
        var TAG = BundleKeyIdentifier.TAG

        fun newInstance(
            position: Int,
            album: Album,
        ) = AlbumsFragment().also { fragment ->
            Bundle().apply {
                putInt(POSITION_ARG, position)
                putParcelable(TAG, album)
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
        triggerAction(AlbumsIntent.GetTopAlbumsByTag)
    }

    private fun getArgs() {
        triggerAction(AlbumsIntent.GetArgs(arguments))
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

    private fun setUIState(it: AlbumsState) {
        when (it) {
            AlbumsState.Error -> {}
            AlbumsState.Idle -> {}
            AlbumsState.Loading -> {}
            AlbumsState.State1 -> {}
            is AlbumsState.ArgumentsProcessed -> {
                binding.tvAlbumText.text = it.album.name
            }
        }
    }

    private fun setUIEffect(it: AlbumsEffect) {
        when (it) {
            AlbumsEffect.Effect1 -> {}
        }
    }

    private fun triggerAction(it: AlbumsIntent) {
        lifecycleScope.launch {
            viewModel.intents.send(it)
        }
    }

}