package com.bimalghara.channelviewcleanarchitecturesolid.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.categories.CategoryEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.episodes.EpisodeEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.type_converters.DataConverters

/**
 * Created by BimalGhara
 */

@Database(
    entities = [EpisodeEntity::class, ChannelEntity::class, CategoryEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataConverters::class)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "mindvalley_app_db"
    }

    abstract val episodesDao: EpisodesDao
    abstract val channelsDao: ChannelsDao
    abstract val categoriesDao: CategoriesDao
}