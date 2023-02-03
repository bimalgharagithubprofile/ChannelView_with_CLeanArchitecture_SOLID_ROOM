package com.bimalghara.channelviewcleanarchitecturesolid.data.di

import android.app.Application
import androidx.room.Room
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.mapper.ErrorMapperImpl
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.mapper.ErrorMapperSource
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.CategoriesLocalDataSource
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.ChannelsLocalDataSource
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.AllSectionsLocalDataImpl
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.EpisodesLocalDataSource
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.database.AppDatabase
import com.bimalghara.channelviewcleanarchitecturesolid.data.network.AllSectionsRemoteDataImpl
import com.bimalghara.channelviewcleanarchitecturesolid.data.network.AllSectionsRemoteDataSource
import com.bimalghara.channelviewcleanarchitecturesolid.data.network.retrofit.ApiServiceGenerator
import com.bimalghara.channelviewcleanarchitecturesolid.data.repository.CategoriesRepositoryImpl
import com.bimalghara.channelviewcleanarchitecturesolid.data.repository.ChannelsRepositoryImpl
import com.bimalghara.channelviewcleanarchitecturesolid.data.repository.EpisodesRepositoryImpl
import com.bimalghara.channelviewcleanarchitecturesolid.data.repository.ErrorDetailsImpl
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.CategoriesRepositorySource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.ChannelsRepositorySource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.EpisodesRepositorySource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.ErrorDetailsSource
import dagger.Binds
import dagger.Module
import dagger.Provides
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



@Module
@InstallIn(SingletonComponent::class)
abstract class DataModuleRepositories {


    @Binds
    @Singleton
    abstract fun provideCategoriesRepository(categoriesRepository: CategoriesRepositoryImpl): CategoriesRepositorySource

    @Binds
    @Singleton
    abstract fun provideChannelsRepository(channelsRepository: ChannelsRepositoryImpl): ChannelsRepositorySource

    @Binds
    @Singleton
    abstract fun provideEpisodesRepository(episodesRepository: EpisodesRepositoryImpl): EpisodesRepositorySource

}



@InstallIn(SingletonComponent::class)
@Module
class DataModuleDataSources {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideEpisodesLocalData(db: AppDatabase): EpisodesLocalDataSource {
        return AllSectionsLocalDataImpl(episodesDao = db.episodesDao, channelsDao = null, categoriesDao = null)
    }
    @Provides
    @Singleton
    fun provideChannelsLocalData(db: AppDatabase): ChannelsLocalDataSource {
        return AllSectionsLocalDataImpl(episodesDao = null, channelsDao = db.channelsDao, categoriesDao = null)
    }
    @Provides
    @Singleton
    fun provideCategoriesLocalData(db: AppDatabase): CategoriesLocalDataSource {
        return AllSectionsLocalDataImpl(episodesDao = null, channelsDao = null, categoriesDao = db.categoriesDao)
    }

    @Provides
    @Singleton
    fun provideAllChannelsRemoteData(serviceGenerator: ApiServiceGenerator): AllSectionsRemoteDataSource {
        return AllSectionsRemoteDataImpl(serviceGenerator)
    }

}