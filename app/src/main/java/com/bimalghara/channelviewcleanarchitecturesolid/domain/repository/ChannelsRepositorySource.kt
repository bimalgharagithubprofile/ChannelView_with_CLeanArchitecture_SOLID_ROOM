package com.bimalghara.channelviewcleanarchitecturesolid.domain.repository

import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by BimalGhara
 */

interface ChannelsRepositorySource {

    suspend fun getCountryListFromNetwork(): List<ChannelEntity>

    //can't be suspending because it's Flow
    fun getCountryListFromLocal(): Flow<List<ChannelEntity>>

}