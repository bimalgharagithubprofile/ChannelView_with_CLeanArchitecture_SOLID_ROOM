package com.bimalghara.channelviewcleanarchitecturesolid.domain.repository

import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by BimalGhara
 */

interface ChannelsRepositorySource {

    suspend fun requestChannelsListFromNetwork()

    //can't be suspending because it's Flow
    fun getChannelsListFromLocal(): Flow<List<ChannelEntity>>

}