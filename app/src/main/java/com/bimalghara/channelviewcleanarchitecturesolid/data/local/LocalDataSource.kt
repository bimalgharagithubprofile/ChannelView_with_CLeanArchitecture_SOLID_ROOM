package com.bimalghara.channelviewcleanarchitecturesolid.data.local

import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.episodes.Episodes
import kotlinx.coroutines.flow.Flow

/**
 * Created by BimalGhara
 */

interface LocalDataSource {

    /*
    * Episodes
    */
    suspend fun saveEpisodes(episodes: List<Episodes>): List<Long>

    //can't be suspending because it's Flow
    fun getEpisodes(): Flow<List<Episodes>>

}