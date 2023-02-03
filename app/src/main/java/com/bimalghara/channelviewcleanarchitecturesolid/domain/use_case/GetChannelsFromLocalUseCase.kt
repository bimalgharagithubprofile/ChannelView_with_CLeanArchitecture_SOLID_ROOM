package com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case

import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.ChannelsRepositorySource
import kotlinx.coroutines.flow.Flow

/**
 * Created by BimalGhara
 */

class GetChannelsFromLocalUseCase(
    private val channelsRepositorySource: ChannelsRepositorySource
) {

    operator fun invoke(): Flow<List<ChannelEntity>> {
        return channelsRepositorySource.getChannelsListFromLocal()

    }

}