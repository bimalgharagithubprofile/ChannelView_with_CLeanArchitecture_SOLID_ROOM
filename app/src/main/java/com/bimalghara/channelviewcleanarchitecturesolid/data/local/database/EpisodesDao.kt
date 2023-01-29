package com.bimalghara.channelviewcleanarchitecturesolid.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.episodes.EpisodeEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by BimalGhara
 */

@Dao
interface EpisodesDao {

    //can't be suspending because it's Flow
    @Query("SELECT * FROM EpisodeEntity")
    fun getEpisodes(): Flow<List<EpisodeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEpisodes(episodes: List<EpisodeEntity>): List<Long>

    @Query("DELETE FROM EpisodeEntity")
    suspend fun truncate()

}