package com.example.starwars.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide


fun loadImage(context: Context, url: String, placeholder: Int, imageView: ImageView) {
    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        .fitCenter()
        .centerCrop()
        .into(imageView)
}