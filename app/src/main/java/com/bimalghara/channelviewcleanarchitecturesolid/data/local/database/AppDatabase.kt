package com.bimalghara.channelviewcleanarchitecturesolid.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.categories.CategoryEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.channels.ChannelEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.episodes.EpisodeEntity

/**
 * Created by BimalGhara
 */

@Database(
    entities = [EpisodeEntity::class, ChannelEntity::class, CategoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "mindvalley_app_db"
    }

    abstract val episodesDao: EpisodesDao
}