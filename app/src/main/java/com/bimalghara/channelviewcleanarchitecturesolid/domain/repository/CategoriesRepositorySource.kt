package com.bimalghara.channelviewcleanarchitecturesolid.domain.repository

import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.categories.CategoryEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by BimalGhara
 */

interface CategoriesRepositorySource {

    suspend fun getCategoryListFromNetwork(): List<CategoryEntity>

    //can't be suspending because it's Flow
    fun getCategoryListFromLocal(): Flow<List<CategoryEntity>>

}