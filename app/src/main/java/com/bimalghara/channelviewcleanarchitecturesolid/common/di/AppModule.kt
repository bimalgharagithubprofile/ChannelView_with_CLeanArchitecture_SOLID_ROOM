package com.bimalghara.channelviewcleanarchitecturesolid.common.di

import android.content.Context
import com.bimalghara.channelviewcleanarchitecturesolid.common.dispatcher.DefaultDispatcherProvider
import com.bimalghara.channelviewcleanarchitecturesolid.common.dispatcher.DispatcherProviderSource
import com.bimalghara.channelviewcleanarchitecturesolid.utils.NetworkConnectivityImpl
import com.bimalghara.channelviewcleanarchitecturesolid.utils.NetworkConnectivitySource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by BimalGhara
 */

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideDefaultDispatcher(): DispatcherProviderSource {
        return DefaultDispatcherProvider()
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivitySource {
        return NetworkConnectivityImpl(context)
    }
}