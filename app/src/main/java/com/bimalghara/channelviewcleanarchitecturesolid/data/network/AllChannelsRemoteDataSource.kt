package com.bimalghara.channelviewcleanarchitecturesolid.data.network

import com.bimalghara.channelviewcleanarchitecturesolid.data.model.categories.CategoriesDTO
import com.bimalghara.channelviewcleanarchitecturesolid.data.model.channels.ChannelsDTO
import com.bimalghara.channelviewcleanarchitecturesolid.data.model.eposodes.EpisodesDTO


/**
 * Created by BimalGhara
 */

interface AllChannelsRemoteDataSource {

    suspend fun requestCategories(): CategoriesDTO

    suspend fun requestChannels(): ChannelsDTO

    suspend fun requestEpisodes(): EpisodesDTO


}
