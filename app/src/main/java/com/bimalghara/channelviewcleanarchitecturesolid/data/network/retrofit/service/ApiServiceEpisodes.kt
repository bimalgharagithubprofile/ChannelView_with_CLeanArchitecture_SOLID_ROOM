package com.bimalghara.channelviewcleanarchitecturesolid.data.network.retrofit.service

import com.bimalghara.channelviewcleanarchitecturesolid.data.model.eposodes.EpisodesDTO
import retrofit2.http.GET

/**
 * Created by BimalGhara
 */

interface ApiServiceEpisodes {


    @GET("raw/z5AExTtw")
    suspend fun getCountryList():List<EpisodesDTO>



}