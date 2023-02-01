package com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case

import com.bimalghara.channelviewcleanarchitecturesolid.common.dispatcher.DispatcherProviderSource
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.CustomException
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.EpisodesRepositorySource
import com.bimalghara.channelviewcleanarchitecturesolid.utils.ResourceWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

/**
 * Created by BimalGhara
 */

class RequestEpisodesFromNetworkUseCase(
    private val dispatcherProviderSource: DispatcherProviderSource,
    private val episodesRepositorySource: EpisodesRepositorySource
) {

    operator fun invoke(): Flow<ResourceWrapper<Int>> = flow {
        emit(ResourceWrapper.Loading())

        try {
            val count = episodesRepositorySource.requestEpisodeListFromNetwork()
            emit(ResourceWrapper.Success(data = count))
        } catch (e: CustomException) {
            emit(ResourceWrapper.Error(e))
        }

    }.flowOn(dispatcherProviderSource.io)


}