package com.example.androidapplicationtemplate.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.androidapplicationtemplate.R
import com.example.androidapplicationtemplate.ui.genreDetails.adapter.GenericAdapter.Companion.CROSS_FADE_ANIMATION_DURATION

const val CROSS_FADE_ANIMATION_DURATION = 400

/**
 * This is a helper class to load images safely.
 * Suppose we have introduced a delay for initializing glide. so its initialisation along with context passing is delayed for that amount of time.
 * Suppose the delay is 5 seconds. Now if the screen is closed within that duration then following will happen in order
 * This is the example code.
 *
 * Handler().postDelayed(
 * {
 * Glide.with(root)
        .load(album.images[0].text ?: "")
        .placeholder(ContextCompat.getDrawable(root.context, R.drawable.place_holder))
        .transition(DrawableTransitionOptions.withCrossFade(
        CROSS_FADE_ANIMATION_DURATION))
        .into(ivAlbum)
  }, 5000)
 *
 *
 * 1. Activity/fragment is closed before 5 seconds so the context is destroyed.
 * 2. Now after the delay the glide is trying to load image using a context which is destroyed.
 * 3. Now the app will throw error that 'You cannot start a load for a destroyed activity'
 *
 * TL;DR
 * so we use the below code in the app to load images
 */


fun ImageView.loadImageSafely(
    url: String?,
    placeHolder: Int = R.drawable.place_holder,
    transition: TransitionOptions<DrawableTransitionOptions, Drawable> =
        DrawableTransitionOptions.withCrossFade(
            CROSS_FADE_ANIMATION_DURATION
        ),
    transitionDuration: Int = CROSS_FADE_ANIMATION_DURATION,
) {
    if ((context as ContextWrapper).baseContext.isValid()) {
        Glide.with(context)
            .load(url ?: "")
            .placeholder(ContextCompat.getDrawable(context, placeHolder))
            .transition(
                transition
            )
            .into(this)
    }
}

fun Context.isValid() = this !is Activity || (!this.isFinishing && !this.isDestroyed)


