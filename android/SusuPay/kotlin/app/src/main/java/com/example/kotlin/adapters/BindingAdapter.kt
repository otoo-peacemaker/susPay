package com.example.kotlin.adapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.kotlin.R
import com.example.kotlin.network.Resource
import retrofit2.Response

@BindingAdapter("Status")
fun bindStatus(statusImageView: ImageView, status: Resource<String>?) {
    when (status) {
        Resource.Loading -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        Resource.Failures(isNetworkError = true) -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        Resource.Success(Response.success("success").message())-> {
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
