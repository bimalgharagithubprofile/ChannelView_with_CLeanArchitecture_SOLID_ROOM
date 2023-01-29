package com.bimalghara.channelviewcleanarchitecturesolid.data.local

import com.bimalghara.channelviewcleanarchitecturesolid.data.local.database.CategoriesDao
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.database.ChannelsDao
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.database.EpisodesDao
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.categories.CategoryEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.episodes.EpisodeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by BimalGhara
 */

class AllChannelsLocalDataImpl @Inject constructor(
    private val episodesDao: EpisodesDao?,
    private val channelsDao: ChannelsDao?,
    private val categoriesDao: CategoriesDao?
) : EpisodesLocalDataSource,
    ChannelsLocalDataSource,
    CategoriesLocalDataSource {

    /*
    * episodes
    */
    override suspend fun saveEpisodes(episodes: List<EpisodeEntity>): List<Long> {
        return episodesDao?.addEpisodes(episodes) ?: emptyList()
    }

    override fun getEpisodes(): Flow<List<EpisodeEntity>> {
        return episodesDao?.getEpisodes() ?: flow { emit(emptyList()) }
    }

    override suspend fun truncateEpisodes() {
        episodesDao?.truncate()
    }

    /*
    * channels
    */
    override suspend fun saveChannels(channels: List<ChannelEntity>): List<Long> {
        return channelsDao?.addChannels(channels) ?: emptyList()
    }

    override fun getChannels(): Flow<List<ChannelEntity>> {
        return channelsDao?.getChannels() ?: flow { emit(emptyList()) }
    }

    override suspend fun truncateChannels() {
        channelsDao?.truncate()
    }

    /*
    * categories
    */
    override suspend fun saveCategories(categories: List<CategoryEntity>): List<Long> {
        return categoriesDao?.addCategories(categories) ?: emptyList()
    }

    override fun getCategories(): Flow<List<CategoryEntity>> {
        return categoriesDao?.getCategories() ?: flow { emit(emptyList()) }
    }

    override suspend fun truncateCategories() {
        categoriesDao?.truncate()
    }

}