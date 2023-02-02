package com.bimalghara.channelviewcleanarchitecturesolid.data.repository

import com.bimalghara.channelviewcleanarchitecturesolid.utils.DataStatus
import com.bimalghara.channelviewcleanarchitecturesolid.utils.FailureType
import com.bimalghara.channelviewcleanarchitecturesolid.utils.TestUtil.dataStatus
import com.bimalghara.channelviewcleanarchitecturesolid.utils.TestUtil.failureType
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.CustomException
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ERROR_DEFAULT
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ERROR_NETWORK_ERROR
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ERROR_SOCKET_TIMEOUT
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelMedia
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.ChannelsRepositorySource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by BimalGhara
 */

class FakeChannelsRepository : ChannelsRepositorySource {
    override suspend fun requestChannelsListFromNetwork(): Int {
        when (dataStatus) {
            DataStatus.Success -> {
                return 2 //two records
            }
            DataStatus.Fail -> {
                when (failureType) {
                    is FailureType.Http -> {
                        throw CustomException(cause = "401")
                    }
                    is FailureType.Timeout -> {
                        throw CustomException(cause = ERROR_SOCKET_TIMEOUT)
                    }
                    is FailureType.Network -> {
                        throw CustomException(cause = ERROR_NETWORK_ERROR)
                    }
                    else -> {
                        throw CustomException(cause = ERROR_DEFAULT)
                    }
                }
            }
            else -> {
                return 0 //no record
            }
        }
    }

    override fun getChannelsListFromLocal(): Flow<List<ChannelEntity>> = flow {
        when (dataStatus) {
            DataStatus.Success -> {
                val mediaList = listOf(
                    ChannelMedia(title = "Media 1", coverAsset = "https://image.com/cover_1", type = "image"),
                    ChannelMedia(title = "Media 2", coverAsset = "https://image.com/cover_2", type = "image")
                )
                val dataList = listOf(
                    ChannelEntity(title = "Channel 1", iconAsset = "https://image.com/icon_1", channelMedia = emptyList()),
                    ChannelEntity(title = "Channel 1", iconAsset = "https://image.com/icon_1", channelMedia = mediaList)
                )
                emit(dataList) //two records
            }
            else -> {
                emit(emptyList()) //no record
            }
        }
    }
}