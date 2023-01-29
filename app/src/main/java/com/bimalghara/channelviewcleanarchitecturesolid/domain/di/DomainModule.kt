package com.bimalghara.channelviewcleanarchitecturesolid.domain.di

import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.ChannelsRepositorySource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.ErrorDetailsSource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case.GetChannelsFromLocalUseCase
import com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case.GetChannelsFromNetworkUseCase
import com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case.GetErrorDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlin.coroutines.CoroutineContext

/**
 * Created by BimalGhara
 */

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Provides
    fun provideGetErrorDetailsUseCase(errorDetailsSource: ErrorDetailsSource): GetErrorDetailsUseCase{
        return GetErrorDetailsUseCase(errorDetailsSource = errorDetailsSource)
    }

    @Provides
    fun provideGetChannelsFromNetworkUseCase(coroutineContext: CoroutineContext, channelsRepositorySource: ChannelsRepositorySource): GetChannelsFromNetworkUseCase {
        return GetChannelsFromNetworkUseCase(ioDispatcher = coroutineContext, channelsRepositorySource = channelsRepositorySource)
    }
    @Provides
    fun provideGetChannelsFromLocalUseCase(coroutineContext: CoroutineContext, channelsRepositorySource: ChannelsRepositorySource): GetChannelsFromLocalUseCase {
        return GetChannelsFromLocalUseCase(ioDispatcher = coroutineContext, channelsRepositorySource = channelsRepositorySource)
    }


}