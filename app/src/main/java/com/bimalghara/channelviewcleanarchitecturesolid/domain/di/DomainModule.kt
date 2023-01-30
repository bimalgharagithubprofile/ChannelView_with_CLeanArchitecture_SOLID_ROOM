package com.bimalghara.channelviewcleanarchitecturesolid.domain.di

import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.CategoriesRepositorySource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.ChannelsRepositorySource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.EpisodesRepositorySource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.ErrorDetailsSource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case.*
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
    fun provideGetCategoriesFromNetworkUseCase(coroutineContext: CoroutineContext, categoriesRepositorySource: CategoriesRepositorySource): RequestCategoriesFromNetworkUseCase {
        return RequestCategoriesFromNetworkUseCase(ioDispatcher = coroutineContext, categoriesRepositorySource = categoriesRepositorySource)
    }
    @Provides
    fun provideGetCategoriesFromLocalUseCase(coroutineContext: CoroutineContext, categoriesRepositorySource: CategoriesRepositorySource): GetCategoriesFromLocalUseCase {
        return GetCategoriesFromLocalUseCase(ioDispatcher = coroutineContext, categoriesRepositorySource = categoriesRepositorySource)
    }

    @Provides
    fun provideGetChannelsFromNetworkUseCase(coroutineContext: CoroutineContext, channelsRepositorySource: ChannelsRepositorySource): RequestChannelsFromNetworkUseCase {
        return RequestChannelsFromNetworkUseCase(ioDispatcher = coroutineContext, channelsRepositorySource = channelsRepositorySource)
    }
    @Provides
    fun provideGetChannelsFromLocalUseCase(coroutineContext: CoroutineContext, channelsRepositorySource: ChannelsRepositorySource): GetChannelsFromLocalUseCase {
        return GetChannelsFromLocalUseCase(ioDispatcher = coroutineContext, channelsRepositorySource = channelsRepositorySource)
    }

    @Provides
    fun provideGetEpisodesFromNetworkUseCase(coroutineContext: CoroutineContext, episodesRepositorySource: EpisodesRepositorySource): RequestEpisodesFromNetworkUseCase {
        return RequestEpisodesFromNetworkUseCase(ioDispatcher = coroutineContext, episodesRepositorySource = episodesRepositorySource)
    }
    @Provides
    fun provideGetEpisodesFromLocalUseCase(coroutineContext: CoroutineContext, episodesRepositorySource: EpisodesRepositorySource): GetEpisodesFromLocalUseCase {
        return GetEpisodesFromLocalUseCase(ioDispatcher = coroutineContext, episodesRepositorySource = episodesRepositorySource)
    }

}