package com.bimalghara.channelviewcleanarchitecturesolid.data.repository

import android.util.Log
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.CustomException
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ERROR_DEFAULT
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.ChannelsLocalDataSource
import com.bimalghara.channelviewcleanarchitecturesolid.data.mapper.toDomain
import com.bimalghara.channelviewcleanarchitecturesolid.data.network.AllSectionsRemoteDataSource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.ChannelsRepositorySource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by BimalGhara
 */

class ChannelsRepositoryImpl @Inject constructor(
    private val remoteDataSource: AllSectionsRemoteDataSource,
    private val localDataSource: ChannelsLocalDataSource
) :
    ChannelsRepositorySource {
    private val logTag = javaClass.simpleName

    override suspend fun requestChannelsListFromNetwork(): Int {
        return try {
            val result = remoteDataSource.requestChannels()

            //convert DTO to Entity
            val convertedChannelsList = result.data.channels.map {
                it.toDomain()
            }

            //cache into local database
            localDataSource.truncateChannels()
            val response = localDataSource.saveChannels(convertedChannelsList)
            if (response.isEmpty()) {
                Log.e(logTag, "Failed to cache Channels Records!")
            }

            convertedChannelsList.size

        } catch (e: CustomException) {
            throw e
        } catch (ex: Exception) {
            throw CustomException(cause = ex.localizedMessage ?: ERROR_DEFAULT)
        }

    }

    override fun getChannelsListFromLocal(): Flow<List<ChannelEntity>> {
        return localDataSource.getChannels()
    }


}

