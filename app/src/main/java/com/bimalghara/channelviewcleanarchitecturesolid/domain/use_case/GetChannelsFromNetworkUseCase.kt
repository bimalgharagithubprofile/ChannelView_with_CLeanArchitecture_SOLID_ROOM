package com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case

import com.bimalghara.channelviewcleanarchitecturesolid.data.error.CustomException
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.ChannelsRepositorySource
import com.bimalghara.channelviewcleanarchitecturesolid.utils.ResourceWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

/**
 * Created by BimalGhara
 */

class GetChannelsFromNetworkUseCase(private val ioDispatcher: CoroutineContext, private val channelsRepositorySource: ChannelsRepositorySource)  {

    operator fun invoke():Flow<ResourceWrapper<List<ChannelEntity>>> = flow {
        emit(ResourceWrapper.Loading())

        try {
            val result = channelsRepositorySource.getCountryListFromNetwork()
            emit(ResourceWrapper.Success(data = result))
        }catch (e: CustomException){
            emit(ResourceWrapper.Error(e))
        }

    }.flowOn(ioDispatcher)


}