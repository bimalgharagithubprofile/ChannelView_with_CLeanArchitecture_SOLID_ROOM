package com.bimalghara.channelviewcleanarchitecturesolid.data.local

import com.bimalghara.channelviewcleanarchitecturesolid.data.local.database.EpisodesDao
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.episodes.EpisodeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by BimalGhara
 */

class LocalDataImpl @Inject constructor(
    private val episodesDao: EpisodesDao
) : LocalDataSource {

    override suspend fun saveEpisodes(episodes: List<EpisodeEntity>): List<Long> {
        return episodesDao.addEpisodes(episodes)
    }

    override fun getEpisodes(): Flow<List<EpisodeEntity>> {
        return episodesDao.getEpisodes()
    }


}