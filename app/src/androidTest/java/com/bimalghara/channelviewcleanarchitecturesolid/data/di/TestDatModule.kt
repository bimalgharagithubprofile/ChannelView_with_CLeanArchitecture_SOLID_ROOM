package com.bimalghara.channelviewcleanarchitecturesolid.data.di

import android.app.Application
import androidx.room.Room
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.AllSectionsLocalDataImpl
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.CategoriesLocalDataSource
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.ChannelsLocalDataSource
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.EpisodesLocalDataSource
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by BimalGhara
 */

/*
@InstallIn(SingletonComponent::class)
@Module
class TestDataModuleDataSources {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
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

}*/
