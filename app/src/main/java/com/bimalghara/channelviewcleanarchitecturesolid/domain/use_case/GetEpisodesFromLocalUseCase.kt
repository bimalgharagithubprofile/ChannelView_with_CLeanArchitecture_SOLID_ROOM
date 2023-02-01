package com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case

import com.bimalghara.channelviewcleanarchitecturesolid.common.dispatcher.DispatcherProviderSource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.episodes.EpisodeEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.EpisodesRepositorySource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

/**
 * Created by BimalGhara
 */

class GetEpisodesFromLocalUseCase(
    private val dispatcherProviderSource: DispatcherProviderSource,
    private val episodesRepositorySource: EpisodesRepositorySource
) {

    operator fun invoke(): Flow<List<EpisodeEntity>> {
        return episodesRepositorySource.getEpisodeListFromLocal().flowOn(dispatcherProviderSource.io)

    }

}