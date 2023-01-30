package com.bimalghara.channelviewcleanarchitecturesolid.data.repository

import android.util.Log
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.CustomException
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ERROR_DEFAULT
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.CategoriesLocalDataSource
import com.bimalghara.channelviewcleanarchitecturesolid.data.mapper.toDomain
import com.bimalghara.channelviewcleanarchitecturesolid.data.network.AllChannelsRemoteDataSource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.categories.CategoryEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.CategoriesRepositorySource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by BimalGhara
 */

class CategoriesRepositoryImpl @Inject constructor(
    private val remoteDataSource: AllChannelsRemoteDataSource,
    private val localDataSource: CategoriesLocalDataSource
) :
    CategoriesRepositorySource {
    private val logTag = javaClass.simpleName

    override suspend fun requestCategoryListFromNetwork() {
        try {
            val result = remoteDataSource.requestCategories()

            //convert DTO to Entity
            val convertedCategoriesList = result.data.categories.map {
                it.toDomain()
            }

            //cache into local database
            localDataSource.truncateCategories()
            val response = localDataSource.saveCategories(convertedCategoriesList)
            if (response.isEmpty()) {
                Log.e(logTag, "Failed to cache Categories Records!")
            }

        } catch (e: CustomException) {
            throw e
        } catch (ex: Exception) {
            throw CustomException(cause = ex.localizedMessage ?: ERROR_DEFAULT)
        }
    }

    override fun getCategoryListFromLocal(): Flow<List<CategoryEntity>> {
        return localDataSource.getCategories()
    }

}

