package com.bimalghara.channelviewcleanarchitecturesolid.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bimalghara.channelviewcleanarchitecturesolid.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar

/**
 * Created by BimalGhara
 */


/**
 * switch between visibility of view
 */
fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toInvisible() {
    this.visibility = View.GONE
}


/**
 * Transforms static java function SnackBar.make() to an extension function on View.
 */
fun View.showSnackBar(snackBarText: String, timeLength: Int) {
    Snackbar.make(this, snackBarText, timeLength).run {
        addCallback(object : Snackbar.Callback() {
            override fun onShown(sb: Snackbar?) {
            }

            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            }
        })
        show()
    }
}


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.showToast(
    lifecycleOwner: LifecycleOwner,
    toastEvent: LiveData<SingleEvent<Any>>,
    timeLength: Int
) {

    toastEvent.observe(lifecycleOwner, Observer { event ->
        event.getContentIfNotHandled()?.let {
            when (it) {
                is String -> Toast.makeText(this.context, it, timeLength).show()
                is Int -> Toast.makeText(this.context, this.context.getString(it), timeLength)
                    .show()
                else -> {
                }
            }
        }
    })
}

fun ImageView.loadImage(resId: Int) = Glide.with(this.context).load(resId).fitCenter().into(this)
fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.mipmap.ic_logo)
        .error(R.mipmap.ic_logo)
        .override(this.width,this.height)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//cache the optimised size data
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}


