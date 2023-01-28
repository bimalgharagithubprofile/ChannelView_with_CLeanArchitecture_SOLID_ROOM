package com.bimalghara.channelviewcleanarchitecturesolid.data.di

import com.bimalghara.channelviewcleanarchitecturesolid.data.error.mapper.ErrorMapperImpl
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.mapper.ErrorMapperSource
import com.bimalghara.channelviewcleanarchitecturesolid.data.repository.ErrorDetailsImpl
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.ErrorDetailsSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by BimalGhara
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModuleErrors {

    //error for the app
    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapperImpl): ErrorMapperSource
    @Binds
    @Singleton
    abstract fun provideErrorDetails(errorDetails: ErrorDetailsImpl): ErrorDetailsSource

}

