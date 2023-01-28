package com.bimalghara.channelviewcleanarchitecturesolid.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.episodes.EpisodeEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by BimalGhara
 */

@Dao
interface EpisodesDao {

    //can't be suspending because it's Flow
    @Query("SELECT * FROM Episodes")
    fun getEpisodes(): Flow<List<EpisodeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //@JvmSuppressWildcards
    suspend fun addEpisodes(episodesData: List<EpisodeEntity>): List<Long>

    @Query("DELETE FROM Episodes")
    suspend fun deleteEpisodes()

}