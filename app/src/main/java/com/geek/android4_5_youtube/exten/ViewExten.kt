package com.geek.android4_5_youtube.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun View.visibility(it: Boolean?) {
    if (it == true) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}