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
    fun provideGetCategoriesFromLocalUseCase(dispatcherProviderSource: DispatcherProviderSource, categoriesRepositorySource: CategoriesRepositorySource): GetCategoriesFromLocalUseCase {
        return GetCategoriesFromLocalUseCase(dispatcherProviderSource = dispatcherProviderSource, categoriesRepositorySource = categoriesRepositorySource)
    }

    @Provides
    fun provideGetChannelsFromNetworkUseCase(dispatcherProviderSource: DispatcherProviderSource, channelsRepositorySource: ChannelsRepositorySource): RequestChannelsFromNetworkUseCase {
        return RequestChannelsFromNetworkUseCase(dispatcherProviderSource = dispatcherProviderSource, channelsRepositorySource = channelsRepositorySource)
    }
    @Provides
    fun provideGetChannelsFromLocalUseCase(dispatcherProviderSource: DispatcherProviderSource, channelsRepositorySource: ChannelsRepositorySource): GetChannelsFromLocalUseCase {
        return GetChannelsFromLocalUseCase(dispatcherProviderSource = dispatcherProviderSource, channelsRepositorySource = channelsRepositorySource)
    }

    @Provides
    fun provideGetEpisodesFromNetworkUseCase(dispatcherProviderSource: DispatcherProviderSource, episodesRepositorySource: EpisodesRepositorySource): RequestEpisodesFromNetworkUseCase {
        return RequestEpisodesFromNetworkUseCase(dispatcherProviderSource = dispatcherProviderSource, episodesRepositorySource = episodesRepositorySource)
    }
    @Provides
    fun provideGetEpisodesFromLocalUseCase(dispatcherProviderSource: DispatcherProviderSource, episodesRepositorySource: EpisodesRepositorySource): GetEpisodesFromLocalUseCase {
        return GetEpisodesFromLocalUseCase(dispatcherProviderSource = dispatcherProviderSource, episodesRepositorySource = episodesRepositorySource)
    }

}