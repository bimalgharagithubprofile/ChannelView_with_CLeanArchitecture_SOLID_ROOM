package com.bimalghara.channelviewcleanarchitecturesolid

import android.app.Application
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by BimalGhara
 */

@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val builder = Picasso.Builder(this).downloader(OkHttp3Downloader(this, Long.MAX_VALUE)).build()
        Picasso.setSingletonInstance(builder)
    }

}