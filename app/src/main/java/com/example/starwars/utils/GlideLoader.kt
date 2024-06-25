package com.example.starwars.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView


fun loadImage(context: Context, url: String, placeholder: Int, imageView: ImageView) {
    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        .fitCenter()
        .centerCrop()
        .into(imageView)
}
fun loadImage(context: Context, drawable: Int, circularImageView: CircleImageView) {
    Glide
        .with(context)
        .load(drawable)
        .fitCenter()
        .centerCrop()
        .into(circularImageView)
}
fun loadImage(context: Context, url: String, circularImageView: CircleImageView) {
    Glide.with(context)
        .load(url)
        .fitCenter()
        .centerCrop()
        .into(circularImageView)
}