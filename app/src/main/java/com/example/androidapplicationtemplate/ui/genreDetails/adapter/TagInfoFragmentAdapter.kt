package com.example.androidapplicationtemplate.ui.genreDetails.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.ui.genreDetails.fragment.AlbumsFragment
import com.example.androidapplicationtemplate.ui.genreDetails.fragment.ArtistsFragment
import com.example.androidapplicationtemplate.ui.genreDetails.fragment.TracksFragment

class TagInfoFragmentAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    val tag: Tag,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AlbumsFragment.newInstance(
                position,
                tag,
            )
            1 -> ArtistsFragment.newInstance(
                position,
                tag,
            )
            2 -> TracksFragment.newInstance(
                position,
                tag,
            )
            else -> AlbumsFragment.newInstance(
                position,
                tag,
            )
        }
    }
}
