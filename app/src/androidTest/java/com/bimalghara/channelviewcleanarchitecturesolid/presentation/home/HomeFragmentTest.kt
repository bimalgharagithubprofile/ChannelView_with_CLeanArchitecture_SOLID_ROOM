package com.bimalghara.channelviewcleanarchitecturesolid.presentation.home

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import com.bimalghara.channelviewcleanarchitecturesolid.R
import com.bimalghara.channelviewcleanarchitecturesolid.data.di.DataModuleDataSources
import com.bimalghara.channelviewcleanarchitecturesolid.data.local.database.AppDatabase
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.episodes.EpisodeEntity
import com.bimalghara.channelviewcleanarchitecturesolid.utils.BaseRobot
import com.bimalghara.channelviewcleanarchitecturesolid.utils.EspressoIdlingResource
import com.bimalghara.channelviewcleanarchitecturesolid.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.IsNot.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

/**
 * Created by BimalGhara
 */
@LargeTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
@UninstallModules(DataModuleDataSources::class)
class HomeFragmentTest {

    //Rule to inject all the dependency as @HiltAndroidTest is not enough for test env
    //Load this Rule at first. so, order = 0
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    private var mIdlingResource: IdlingResource? = null

    @Inject
    lateinit var db: AppDatabase

    @Before
    fun setUp(){
        hiltRule.inject()

        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)

        launchFragmentInHiltContainer<HomeFragment>(Bundle(), R.style.Theme_ChannelViewCleanArchitectureSolid)
    }

    @After
    fun tearDown() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister()
        }
    }

    @Test
    fun initiallyShimmerLoadingDisplayed() {
        BaseRobot().assertOnView(withId(R.id.shimmer), matches(isDisplayed()))
    }

    @Test
    fun displayEpisodesList_fromCache() {
        BaseRobot().assertOnView(withId(R.id.shimmer), matches(isDisplayed()))

        //insert two dummy data
        runBlocking {
            db.episodesDao.addEpisodes(
                listOf(
                    EpisodeEntity(title = "Dummy Title 1", channel = "Awesome One", coverAsset = "https://assets.mindvalley.com/api/v1/assets/5bdbdd0e-3bd3-432b-b8cb-3d3556c58c94.jpg?transform=w_1080"),
                    EpisodeEntity(title = "Dummy Title 2", channel = "Awesome Two", coverAsset = "https://assets.mindvalley.com/api/v1/assets/273a5e20-8088-4e94-8f34-6b0241e93962.jpg?transform=w_1080")
                )
            )
        }

        BaseRobot().assertOnView(withId(R.id.shimmer), matches(not(isDisplayed())))
        BaseRobot().assertOnView(withId(R.id.rvAllSections), matches(isDisplayed()))
        BaseRobot().assertOnView(withText(R.string.new_episodes), matches(isDisplayed()))
        BaseRobot().assertOnView(withId(R.id.rvAllSections), matches(hasChildCount(1)))
        BaseRobot().assertOnView(withText("Dummy Title 1"), matches(isDisplayed()))

    }


    @Test
    fun pullToRefresh_EpisodesList_fromNetwork() {
        BaseRobot().assertOnView(withId(R.id.shimmer), matches(isDisplayed()))

        BaseRobot().doOnView(withId(R.id.swipeContainer), swipeDown())

        BaseRobot().assertOnView(withId(R.id.shimmer), matches(not(isDisplayed())))
        BaseRobot().assertOnView(withText(R.string.new_episodes), matches(isDisplayed()))
        BaseRobot().assertOnView(withId(R.id.rvAllSections), matches(hasChildCount(2)))
        BaseRobot().assertOnView(withText("Conscious Parenting"), matches(isDisplayed()))
        BaseRobot().assertOnView(withText("Raising Kids With Healthy Beliefs"), matches(isDisplayed()))

    }



}