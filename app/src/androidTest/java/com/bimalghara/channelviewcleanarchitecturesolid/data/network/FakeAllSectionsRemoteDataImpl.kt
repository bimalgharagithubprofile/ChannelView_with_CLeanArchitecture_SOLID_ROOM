package com.bimalghara.channelviewcleanarchitecturesolid.data.network

import com.bimalghara.channelviewcleanarchitecturesolid.data.model.categories.CategoriesDTO
import com.bimalghara.channelviewcleanarchitecturesolid.data.model.channels.ChannelsDTO
import com.bimalghara.channelviewcleanarchitecturesolid.data.model.eposodes.EpisodesDTO
import com.bimalghara.channelviewcleanarchitecturesolid.data.network.retrofit.ApiServiceGenerator
import com.bimalghara.sharedtest.sharedUtils.TestHelper
import com.google.gson.Gson
import javax.inject.Inject

/**
 * Created by BimalGhara
 */

class FakeAllSectionsRemoteDataImpl @Inject constructor(
    private val serviceGenerator: ApiServiceGenerator
) : SafeApiRequest(), AllSectionsRemoteDataSource {

    override suspend fun requestCategories(): CategoriesDTO {
        val content = TestHelper.readFileFromResource("/categories.json")
        return Gson().fromJson(content, CategoriesDTO::class.java)
    }

    override suspend fun requestChannels(): ChannelsDTO {
        val content = TestHelper.readFileFromResource("/channels.json")
        return Gson().fromJson(content, ChannelsDTO::class.java)
    }

    override suspend fun requestEpisodes(): EpisodesDTO {
        val content = TestHelper.readFileFromResource("/episodes.json")
        return Gson().fromJson(content, EpisodesDTO::class.java)
    }

}
