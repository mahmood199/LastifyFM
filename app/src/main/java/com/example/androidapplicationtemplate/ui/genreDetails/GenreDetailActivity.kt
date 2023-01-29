package com.example.androidapplicationtemplate.ui.genreDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidapplicationtemplate.R
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.databinding.ActivityGenreDetailBinding
import com.example.androidapplicationtemplate.ui.genreDetails.adapter.TagInfoFragmentAdapter
import com.example.androidapplicationtemplate.ui.genreDetails.effect.GenreDetailEffect
import com.example.androidapplicationtemplate.ui.genreDetails.intent.GenreDetailIntent
import com.example.androidapplicationtemplate.ui.genreDetails.state.GenreDetailState
import com.example.androidapplicationtemplate.ui.genreDetails.viewmodel.GenreDetailViewModel
import com.example.androidapplicationtemplate.util.SnackBarBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GenreDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGenreDetailBinding
    private val viewModel: GenreDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViews()
        setObservers()
        getArgs()
    }

    private fun getArgs() {
        triggerAction(GenreDetailIntent.ParseArgs(intent))
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
        when (it) {
            is GenreDetailState.Error -> {
                showError()
            }
            GenreDetailState.Idle -> {

            }
            GenreDetailState.Loading -> {

            }
            GenreDetailState.State1 -> {

            }
            is GenreDetailState.ArgumentsParsed -> {
                showFragmentsNow(it.tag)
                getTagDetails()
            }
            is GenreDetailState.SetTagDescription -> {
                setTagDescription(it.tag)
            }
        }
    }

    private fun showFragmentsNow(tag: Tag) {
        val adapter = TagInfoFragmentAdapter(
            supportFragmentManager,
            lifecycle,
            tag)
        binding.vp2Tag.offsetLeftAndRight(3)
        binding.vp2Tag.adapter = adapter
        setTabs()
    }

    private fun setTabs() {
        binding.apply {
            val tabList = getTabList()
            tabList.forEach {
                binding.tlTagContentsTitle.addTab(binding.tlTagContentsTitle.newTab().apply {
                    text = it
                })
            }
            TabLayoutMediator(this.tlTagContentsTitle, this.vp2Tag) { tab, position ->
                tab.text = tabList[position]
            }.attach()
        }
    }

    private fun getTabList(): List<String> {
        return listOf("Albums", "Artists", "Tracks")
    }

    private fun setTagDescription(tag: Tag) {
        Toast.makeText(this, tag.name, Toast.LENGTH_SHORT).show()
        binding.apply {
            tvTagName.text = tag.name
            tvTagInfo.text = tag.wiki?.content?.substring(0, 200) ?: ""
        }
    }

    private fun getTagDetails() {
        triggerAction(GenreDetailIntent.GetTagInfo)
        triggerAction(GenreDetailIntent.GetTopAlbumsByTag)
        triggerAction(GenreDetailIntent.GetTopArtistsByTag)
        triggerAction(GenreDetailIntent.GetTopTracksByTag)
    }


    private fun setUIEffect(it: GenreDetailEffect) {
        when (it) {
            is GenreDetailEffect.Effect1 -> {
                SnackBarBuilder.getSnackbar(this, "OKAY", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun showError() {
        SnackBarBuilder.getSnackbar(this,
            getString(R.string.error_something_went_wrong),
            Snackbar.LENGTH_SHORT).show()
    }


    private fun triggerAction(it: GenreDetailIntent) {
        lifecycleScope.launch {
            viewModel.intents.send(it)
        }
    }

}