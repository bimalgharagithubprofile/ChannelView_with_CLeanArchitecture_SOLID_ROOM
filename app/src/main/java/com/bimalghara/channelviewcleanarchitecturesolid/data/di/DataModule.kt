package com.bimalghara.channelviewcleanarchitecturesolid.data.di

import android.app.Application
import androidx.room.Room
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.mapper.ErrorMapperImpl
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.mapper.ErrorMapperSource
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.LocalDataImpl
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.LocalDataSource
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
    fun provideLocalData(db: AppDatabase): LocalDataSource {
        return LocalDataImpl(db.episodesDao)
    }

}