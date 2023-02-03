package com.bimalghara.channelviewcleanarchitecturesolid.presentation.home

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
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

        scenario = launchFragmentInContainer(themeResId = R.style.Theme_ChannelViewCleanArchitectureSolid, initialState = Lifecycle.State.INITIALIZED)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun displayEpisodesList() {
        onView(withId(R.id.tvScreenTittle)).check(matches(withText("Channels")))
    }

}