package com.bimalghara.channelviewcleanarchitecturesolid.data.local

import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by BimalGhara
 */

interface ChannelsLocalDataSource {

    suspend fun saveChannels(channels: List<ChannelEntity>): List<Long>

    //can't be suspending because it's Flow
    fun getChannels(): Flow<List<ChannelEntity>>

    suspend fun truncateChannels()

}