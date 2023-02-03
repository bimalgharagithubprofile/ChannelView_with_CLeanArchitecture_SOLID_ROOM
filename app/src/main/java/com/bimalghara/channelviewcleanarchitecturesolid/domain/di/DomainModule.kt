package com.bimalghara.channelviewcleanarchitecturesolid.domain.di

import com.bimalghara.channelviewcleanarchitecturesolid.common.dispatcher.DispatcherProviderSource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.CategoriesRepositorySource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.ChannelsRepositorySource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.EpisodesRepositorySource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.ErrorDetailsSource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by BimalGhara
 */

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Provides
    fun provideGetErrorDetailsUseCase(dispatcherProviderSource: DispatcherProviderSource, errorDetailsSource: ErrorDetailsSource): GetErrorDetailsUseCase{
        return GetErrorDetailsUseCase(dispatcherProviderSource = dispatcherProviderSource, errorDetailsSource = errorDetailsSource)
    }

    @Provides
    fun provideGetCategoriesFromNetworkUseCase(dispatcherProviderSource: DispatcherProviderSource, categoriesRepositorySource: CategoriesRepositorySource): RequestCategoriesFromNetworkUseCase {
        return RequestCategoriesFromNetworkUseCase(dispatcherProviderSource = dispatcherProviderSource, categoriesRepositorySource = categoriesRepositorySource)
    }
    @Provides
    fun provideGetCategoriesFromLocalUseCase(categoriesRepositorySource: CategoriesRepositorySource): GetCategoriesFromLocalUseCase {
        return GetCategoriesFromLocalUseCase(categoriesRepositorySource = categoriesRepositorySource)
    }

    @Provides
    fun provideGetChannelsFromNetworkUseCase(dispatcherProviderSource: DispatcherProviderSource, channelsRepositorySource: ChannelsRepositorySource): RequestChannelsFromNetworkUseCase {
        return RequestChannelsFromNetworkUseCase(dispatcherProviderSource = dispatcherProviderSource, channelsRepositorySource = channelsRepositorySource)
    }
    @Provides
    fun provideGetChannelsFromLocalUseCase(channelsRepositorySource: ChannelsRepositorySource): GetChannelsFromLocalUseCase {
        return GetChannelsFromLocalUseCase(channelsRepositorySource = channelsRepositorySource)
    }

    @Provides
    fun provideGetEpisodesFromNetworkUseCase(dispatcherProviderSource: DispatcherProviderSource, episodesRepositorySource: EpisodesRepositorySource): RequestEpisodesFromNetworkUseCase {
        return RequestEpisodesFromNetworkUseCase(dispatcherProviderSource = dispatcherProviderSource, episodesRepositorySource = episodesRepositorySource)
    }
    @Provides
    fun provideGetEpisodesFromLocalUseCase(episodesRepositorySource: EpisodesRepositorySource): GetEpisodesFromLocalUseCase {
        return GetEpisodesFromLocalUseCase(episodesRepositorySource = episodesRepositorySource)
    }

}