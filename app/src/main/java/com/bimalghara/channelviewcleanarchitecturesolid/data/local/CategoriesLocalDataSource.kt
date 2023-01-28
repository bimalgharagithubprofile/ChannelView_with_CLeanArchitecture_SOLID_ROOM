package com.bimalghara.channelviewcleanarchitecturesolid.data.local

import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.categories.CategoryEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by BimalGhara
 */

interface CategoriesLocalDataSource {

    suspend fun saveCategories(categories: List<CategoryEntity>): List<Long>

    //can't be suspending because it's Flow
    fun getCategories(): Flow<List<CategoryEntity>>

    suspend fun truncateCategories()

}