package com.bimalghara.channelviewcleanarchitecturesolid.presentation.home

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import com.bimalghara.channelviewcleanarchitecturesolid.R
import com.bimalghara.channelviewcleanarchitecturesolid.data.di.DataModuleDataSources
import com.bimalghara.channelviewcleanarchitecturesolid.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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


    @Before
    fun setUp(){
        hiltRule.inject()

        launchFragmentInHiltContainer<HomeFragment>(Bundle(), R.style.Theme_ChannelViewCleanArchitectureSolid)
    }

    @Test
    fun displayShimmerLoadingAnimation() {
        onView(withId(R.id.shimmer)).check(matches(isDisplayed()))
    }

    @Test
    fun displayEpisodesList() {
        onView(withId(R.id.shimmer)).check(matches(isDisplayed()))
        onView(withId(R.id.shimmer)).check(matches(not(isDisplayed())))
        onView(withText(R.string.new_episodes)).check(matches(isDisplayed()))
    }

}