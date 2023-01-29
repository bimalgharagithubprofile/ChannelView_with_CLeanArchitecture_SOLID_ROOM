package com.bimalghara.channelviewcleanarchitecturesolid.data.network.retrofit.service

import com.bimalghara.channelviewcleanarchitecturesolid.data.model.channels.ChannelsDTO
import retrofit2.http.GET

/**
 * Created by BimalGhara
 */

interface ApiServiceChannels {


    @GET("raw/Xt12uVhM")
    suspend fun getChannelList():List<ChannelsDTO>



}