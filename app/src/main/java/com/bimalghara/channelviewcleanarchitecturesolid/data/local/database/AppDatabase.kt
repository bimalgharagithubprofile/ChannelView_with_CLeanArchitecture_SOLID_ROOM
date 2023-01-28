package com.bimalghara.channelviewcleanarchitecturesolid.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.episodes.Episodes

/**
 * Created by BimalGhara
 */

@Database(
    entities = [Episodes::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "mindvalley_app_db"
    }

    abstract val episodesDao: EpisodesDao
}