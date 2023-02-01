package com.bimalghara.channelviewcleanarchitecturesolid.domain.repository

import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.episodes.EpisodeEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by BimalGhara
 */

interface EpisodesRepositorySource {

    suspend fun requestEpisodeListFromNetwork(): Int

    //can't be suspending because it's Flow
    fun getEpisodeListFromLocal(): Flow<List<EpisodeEntity>>

}