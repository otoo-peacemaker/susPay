
package com.example.module1.adapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.architecturaltemplate.network.Status
import com.example.module1.R

/*
*/
/**
 * When there is no Mars property data (data is null), hide the [RecyclerView], otherwise show it.
 *//*
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsProperty>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

*/
/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 *//*
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

/**
 * This binding adapter displays the [ApiStatus] of the network request in an image view.  When
 * the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
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
