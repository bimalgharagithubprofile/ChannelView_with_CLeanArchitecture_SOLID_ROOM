package com.bimalghara.channelviewcleanarchitecturesolid.presentation.home

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import com.bimalghara.channelviewcleanarchitecturesolid.R
import org.junit.Test

/**
 * Created by BimalGhara
 */

@HiltAndroidTest
class HomeFragmentTest {

    //Rule to inject all the dependency as @HiltAndroidTest is not enough for test env
    //Load this Rule at first. so, order = 0
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    private lateinit var scenario: FragmentScenario<HomeFragment>


    @Before
    fun setUp(){
        hiltRule.inject()

        scenario = launchFragmentInContainer(themeResId = R.style.Theme_ChannelViewCleanArchitectureSolid)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun displayEpisodesList() {

    }

}