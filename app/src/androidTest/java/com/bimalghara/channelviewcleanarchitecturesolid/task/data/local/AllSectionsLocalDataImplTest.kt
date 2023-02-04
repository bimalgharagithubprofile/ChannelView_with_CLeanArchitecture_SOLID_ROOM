package com.bimalghara.channelviewcleanarchitecturesolid.task.data.local

import app.cash.turbine.test
import com.bimalghara.channelviewcleanarchitecturesolid.data.di.DataModuleDataSources
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.AllSectionsLocalDataImpl
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.database.AppDatabase
import com.bimalghara.channelviewcleanarchitecturesolid.data.mapper.toDomain
import com.bimalghara.channelviewcleanarchitecturesolid.data.model.eposodes.EpisodesDTO
import com.bimalghara.sharedtest.sharedUtils.TestHelper
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

/**
 * Created by BimalGhara
 */

@ExperimentalCoroutinesApi
@HiltAndroidTest
@UninstallModules(DataModuleDataSources::class)
class AllSectionsLocalDataImplTest {

    //Rule to inject all the dependency as @HiltAndroidTest is not enough for test env
    //Load this Rule at first. so, order = 0
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var db: AppDatabase

    private lateinit var allSectionsLocalDataImpl: AllSectionsLocalDataImpl

    @Before
    fun setUp() {
        hiltRule.inject()

        allSectionsLocalDataImpl = AllSectionsLocalDataImpl(db.episodesDao, db.channelsDao, db.categoriesDao)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun saveTwoEpisodes_expectedTwoRecordsSaved() = runBlocking {
        val content = TestHelper.readFileFromResource("/episodes.json")
        val episodesDTO = Gson().fromJson(content, EpisodesDTO::class.java)
        val convertedEpisodesList = episodesDTO.data.media.map {
            it.toDomain()
        }

        allSectionsLocalDataImpl.saveEpisodes(convertedEpisodesList)

        allSectionsLocalDataImpl.getEpisodes().test {
            val episodesList = awaitItem()

            assertThat(episodesList.size).isAtLeast(2)

            cancel()
        }
    }

}