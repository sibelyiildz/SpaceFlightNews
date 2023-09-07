package com.example.spaceflightnewsapp.extension

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

fun ImageView.setImageUrl(context: Context, url: String?) {
    Glide.with(context).load(url).into(this)
}

fun TextView.setColor(color: Int) {
    this.setTextColor(ContextCompat.getColor(context, color))
}
