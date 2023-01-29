package com.example.androidapplicationtemplate.ui.genreDetails.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.ui.genreDetails.fragment.AlbumsFragment

class TagInfoFragmentAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    val list: List<Tag>,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment =
        AlbumsFragment.newInstance(
            position,
            list[position].name,
        )
}
