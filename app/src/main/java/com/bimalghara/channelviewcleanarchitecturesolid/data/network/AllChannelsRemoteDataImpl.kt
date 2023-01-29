package com.bimalghara.channelviewcleanarchitecturesolid.data.network


import com.bimalghara.channelviewcleanarchitecturesolid.data.model.categories.CategoriesDTO
import com.bimalghara.channelviewcleanarchitecturesolid.data.model.channels.ChannelsDTO
import com.bimalghara.channelviewcleanarchitecturesolid.data.model.eposodes.EpisodesDTO
import com.bimalghara.channelviewcleanarchitecturesolid.data.network.retrofit.ApiServiceGenerator
import com.bimalghara.channelviewcleanarchitecturesolid.data.network.retrofit.service.ApiServiceCategories
import com.bimalghara.channelviewcleanarchitecturesolid.data.network.retrofit.service.ApiServiceChannels
import com.bimalghara.channelviewcleanarchitecturesolid.data.network.retrofit.service.ApiServiceEpisodes
import javax.inject.Inject

/**
 * Created by BimalGhara
 */

class AllChannelsRemoteDataImpl @Inject constructor(
    private val serviceGenerator: ApiServiceGenerator
) : SafeApiRequest(), AllChannelsRemoteDataSource {

    override suspend fun requestCategories(): List<CategoriesDTO> {
        val categoriesService = serviceGenerator.createApiService(ApiServiceCategories::class.java)

        return apiRequest(categoriesService::getCategoryList)
    }

    override suspend fun requestChannels(): List<ChannelsDTO> {
        val channelsService = serviceGenerator.createApiService(ApiServiceChannels::class.java)

        return apiRequest(channelsService::getChannelList)
    }

    override suspend fun requestEpisodes(): List<EpisodesDTO> {
        val episodesService = serviceGenerator.createApiService(ApiServiceEpisodes::class.java)

        return apiRequest(episodesService::getEpisodeList)
    }

}
