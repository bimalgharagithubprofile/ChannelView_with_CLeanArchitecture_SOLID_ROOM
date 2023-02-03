package com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case

import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.episodes.EpisodeEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.EpisodesRepositorySource
import kotlinx.coroutines.flow.Flow

/**
 * Created by BimalGhara
 */

class GetEpisodesFromLocalUseCase(
    private val episodesRepositorySource: EpisodesRepositorySource
) {

    operator fun invoke(): Flow<List<EpisodeEntity>> {
        return episodesRepositorySource.getEpisodeListFromLocal()

    }

}