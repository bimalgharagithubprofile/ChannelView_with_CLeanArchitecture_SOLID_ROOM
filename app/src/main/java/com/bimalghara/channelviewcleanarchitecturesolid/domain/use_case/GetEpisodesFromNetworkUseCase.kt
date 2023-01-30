package com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case

import com.bimalghara.channelviewcleanarchitecturesolid.data.error.CustomException
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.episodes.EpisodeEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.EpisodesRepositorySource
import com.bimalghara.channelviewcleanarchitecturesolid.utils.ResourceWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

/**
 * Created by BimalGhara
 */

class GetEpisodesFromNetworkUseCase(
    private val ioDispatcher: CoroutineContext,
    private val episodesRepositorySource: EpisodesRepositorySource
) {

    operator fun invoke(): Flow<ResourceWrapper<Boolean>> = flow {
        emit(ResourceWrapper.Loading())

        try {
            episodesRepositorySource.getEpisodeListFromNetwork()
            emit(ResourceWrapper.Success(data = true))
        } catch (e: CustomException) {
            emit(ResourceWrapper.Error(e))
        }

    }.flowOn(ioDispatcher)


}