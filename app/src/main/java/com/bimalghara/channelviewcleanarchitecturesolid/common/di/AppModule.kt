package com.bimalghara.channelviewcleanarchitecturesolid.common.di

import android.content.Context
import com.bimalghara.channelviewcleanarchitecturesolid.utils.NetworkConnectivityImpl
import com.bimalghara.channelviewcleanarchitecturesolid.utils.NetworkConnectivitySource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/* instantiate class */
@InstallIn(SingletonComponent::class)
@Module
class AppModuleNetworkConnectivity {

    @Provides
    @Singleton
    fun provideCoroutineContextIO(): CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivitySource {
        return NetworkConnectivityImpl(context)
    }
}