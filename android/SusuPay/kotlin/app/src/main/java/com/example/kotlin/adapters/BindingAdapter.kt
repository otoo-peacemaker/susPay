package com.example.kotlin.adapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.gms.fido.fido2.api.common.RequestOptions

@BindingAdapter("Status")
fun bindStatus(statusImageView: ImageView, status: Status?) {
    when (status) {
        Status.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        Status.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        Status.SUCCESS -> {
            statusImageView.visibility = View.GONE
        }
        else -> {
            print("NOTHING")
        }
    }
}

/*

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
                .load(imgUri)
                .apply(RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(imgView)
    }
}*/
