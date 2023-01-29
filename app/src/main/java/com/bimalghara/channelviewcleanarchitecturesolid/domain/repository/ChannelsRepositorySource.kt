package com.bimalghara.channelviewcleanarchitecturesolid.domain.repository

import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelEntity


/**
 * Created by BimalGhara
 */

interface ChannelsRepositorySource {

    suspend fun getCountryListFromNetwork(): List<ChannelEntity>

    suspend fun getCountryListFromLocal(): List<ChannelEntity>

}