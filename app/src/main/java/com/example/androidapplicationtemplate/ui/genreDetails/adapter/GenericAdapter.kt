package com.example.androidapplicationtemplate.ui.genreDetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.androidapplicationtemplate.R
import com.example.androidapplicationtemplate.data.models.response.Album
import com.example.androidapplicationtemplate.data.models.response.Artist
import com.example.androidapplicationtemplate.data.models.response.Track
import com.example.androidapplicationtemplate.databinding.LayoutItemAlbumBinding
import com.example.androidapplicationtemplate.databinding.LayoutItemArtistsBinding
import com.example.androidapplicationtemplate.databinding.LayoutItemTracksBinding

class GenericAdapter(
    val clickAction: (RecyclerViewItemClickAction) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val CROSS_FADE_ANIMATION_DURATION = 400
    }

    private val list = mutableListOf<AdapterItems>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.layout_item_album -> AlbumViewHolder(LayoutItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context)
            ))
            R.layout.layout_item_artists -> ArtistViewHolder(LayoutItemArtistsBinding.inflate(
                LayoutInflater.from(parent.context)
            ))
            else -> TrackViewHolder(LayoutItemTracksBinding.inflate(
                LayoutInflater.from(parent.context)
            ))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AlbumViewHolder -> holder.bind(list[position] as Album)
            is ArtistViewHolder -> holder.bind(list[position] as Artist)
            is TrackViewHolder -> holder.bind(list[position] as Track)
        }
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is Album)
            R.layout.layout_item_album
        else if (list[position] is Artist)
            R.layout.layout_item_artists
        else
            R.layout.layout_item_tracks
    }

    fun addItems(newItems: List<AdapterItems>) {
        val size = this.list.size
        this.list.addAll(newItems)
        notifyItemRangeChanged(size, newItems.size)
    }


    inner class AlbumViewHolder(private val binding: LayoutItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album) {
            binding.apply {
                root.setOnClickListener {
                    clickAction.invoke(RecyclerViewItemClickAction.AlbumClicked(album))
                }
                tvAlbum.text = album.name
                Glide.with(root)
                    .load(album.images[0].text ?: "")
                    .placeholder(ContextCompat.getDrawable(root.context, R.drawable.place_holder))
                    .transition(DrawableTransitionOptions.withCrossFade(
                        CROSS_FADE_ANIMATION_DURATION))
                    .into(ivAlbum)
            }
        }
    }

    inner class ArtistViewHolder(private val binding: LayoutItemArtistsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(artist: Artist) {
            binding.apply {
                root.setOnClickListener {
                    clickAction.invoke(RecyclerViewItemClickAction.ArtistClicked(artist))
                }
                tvArtist.text = artist.name
                Glide.with(root)
                    .load(artist.image[0].text ?: "")
                    .placeholder(ContextCompat.getDrawable(root.context, R.drawable.place_holder))
                    .transition(DrawableTransitionOptions.withCrossFade(
                        CROSS_FADE_ANIMATION_DURATION))
                    .into(ivArtist)
            }
        }
    }

    inner class TrackViewHolder(private val binding: LayoutItemTracksBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(track: Track) {
            binding.apply {
                root.setOnClickListener {
                    clickAction.invoke(RecyclerViewItemClickAction.TrackClicked(track))
                }
                tvTrack.text = track.name
                Glide.with(root)
                    .load(track.image[0].text ?: "")
                    .placeholder(ContextCompat.getDrawable(root.context, R.drawable.place_holder))
                    .transition(DrawableTransitionOptions.withCrossFade(
                        CROSS_FADE_ANIMATION_DURATION))
                    .into(ivTrack)
            }
        }
    }


}