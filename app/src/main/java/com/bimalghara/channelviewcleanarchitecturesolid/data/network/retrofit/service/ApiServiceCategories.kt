package com.bimalghara.channelviewcleanarchitecturesolid.data.network.retrofit.service

import com.bimalghara.channelviewcleanarchitecturesolid.data.model.categories.CategoriesDTO
import retrofit2.http.GET

/**
 * Created by BimalGhara
 */

interface ApiServiceCategories {


    @GET("raw/A0CgArX3")
    suspend fun getCategoryList():CategoriesDTO



}