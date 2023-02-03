package com.bimalghara.channelviewcleanarchitecturesolid.presentation.home

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import com.bimalghara.channelviewcleanarchitecturesolid.R
import com.bimalghara.channelviewcleanarchitecturesolid.utils.launchFragmentInHiltContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

/**
 * Created by BimalGhara
 */
@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
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
    fun displayEpisodesList() {
        onView(withId(R.id.tvScreenTittle)).check(matches(withText("Channels")))
    }

}