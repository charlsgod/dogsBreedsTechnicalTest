package com.example.dogsbreedtechnicaltest.utilities

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target
import com.example.dogsbreedtechnicaltest.R

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("android:imageURL")
    fun setImageURL(imageView: ImageView, URL: String?) {
        Glide.with(imageView.context)
                .load(URL)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.ic_placeholder)
                .into(imageView)
    }
}