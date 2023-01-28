package com.bimalghara.channelviewcleanarchitecturesolid.data.di

import android.app.Application
import androidx.room.Room
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.mapper.ErrorMapperImpl
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.mapper.ErrorMapperSource
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.CategoriesLocalDataSource
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.ChannelsLocalDataSource
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.AllChannelsLocalDataImpl
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.EpisodesLocalDataSource
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.database.AppDatabase
import com.bimalghara.channelviewcleanarchitecturesolid.data.repository.ErrorDetailsImpl
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
        return AllChannelsLocalDataImpl(episodesDao = db.episodesDao, channelsDao = null, categoriesDao = null)
    }
    @Provides
    @Singleton
    fun provideChannelsLocalData(db: AppDatabase): ChannelsLocalDataSource {
        return AllChannelsLocalDataImpl(episodesDao = null, channelsDao = db.channelsDao, categoriesDao = null)
    }
    @Provides
    @Singleton
    fun provideCategoriesLocalData(db: AppDatabase): CategoriesLocalDataSource {
        return AllChannelsLocalDataImpl(episodesDao = null, channelsDao = null, categoriesDao = db.categoriesDao)
    }


}