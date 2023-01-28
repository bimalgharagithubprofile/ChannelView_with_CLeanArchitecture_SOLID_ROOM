package com.bimalghara.channelviewcleanarchitecturesolid.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.channels.ChannelEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by BimalGhara
 */

@Dao
interface ChannelsDao {

    //can't be suspending because it's Flow
    @Query("SELECT * FROM ChannelEntity")
    fun getChannels(): Flow<List<ChannelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addChannels(channels: List<ChannelEntity>): List<Long>

    @Query("DELETE FROM ChannelEntity")
    suspend fun truncate()

}