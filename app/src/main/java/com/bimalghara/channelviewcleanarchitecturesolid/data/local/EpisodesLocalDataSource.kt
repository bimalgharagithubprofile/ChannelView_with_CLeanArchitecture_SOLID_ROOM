package com.bimalghara.channelviewcleanarchitecturesolid.data.local

import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.episodes.EpisodeEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by BimalGhara
 */

interface EpisodesLocalDataSource {

    suspend fun saveEpisodes(episodes: List<EpisodeEntity>): List<Long>

    //can't be suspending because it's Flow
    fun getEpisodes(): Flow<List<EpisodeEntity>>

    suspend fun truncateEpisodes()

}