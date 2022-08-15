package com.rappi.movie.ui.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.rappi.movie.R


@BindingAdapter("srcUrl")
fun ImageView.setImage(url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.loader)
        .into(this)
}