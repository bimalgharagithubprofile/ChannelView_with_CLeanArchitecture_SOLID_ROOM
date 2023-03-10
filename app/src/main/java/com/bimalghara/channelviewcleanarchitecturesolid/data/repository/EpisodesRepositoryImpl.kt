package com.bimalghara.channelviewcleanarchitecturesolid.data.repository

import android.util.Log
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.CustomException
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ERROR_DEFAULT
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.EpisodesLocalDataSource
import com.bimalghara.channelviewcleanarchitecturesolid.data.mapper.toDomain
import com.bimalghara.channelviewcleanarchitecturesolid.data.network.AllSectionsRemoteDataSource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.episodes.EpisodeEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.EpisodesRepositorySource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * Created by BimalGhara
 */

class EpisodesRepositoryImpl @Inject constructor(
    private val remoteDataSource: AllSectionsRemoteDataSource,
    private val localDataSource: EpisodesLocalDataSource
) :
    EpisodesRepositorySource {
    private val logTag = javaClass.simpleName

    override suspend fun requestEpisodeListFromNetwork(): Int {
        return try {
            val result = remoteDataSource.requestEpisodes()

            //convert DTO to Entity
            val convertedEpisodesList = result.data.media.map {
                it.toDomain()
            }

            //cache into local database
            localDataSource.truncateEpisodes()
            val response = localDataSource.saveEpisodes(convertedEpisodesList)
            if (response.isEmpty()) {
                Log.e(logTag, "Failed to cache Episodes Records!")
            }

            convertedEpisodesList.size

        } catch (e: CustomException) {
            throw e
        } catch (ex: Exception) {
            throw CustomException(cause = ex.localizedMessage ?: ERROR_DEFAULT)
        }
    }

    override fun getEpisodeListFromLocal(): Flow<List<EpisodeEntity>> {
        return localDataSource.getEpisodes()
    }

}

