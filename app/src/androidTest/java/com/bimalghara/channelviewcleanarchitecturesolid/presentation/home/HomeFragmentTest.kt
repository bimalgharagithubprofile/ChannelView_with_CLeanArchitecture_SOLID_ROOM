package com.bimalghara.channelviewcleanarchitecturesolid.presentation.home

import com.bimalghara.channelviewcleanarchitecturesolid.data.di.DataModuleDataSources
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule

/**
 * Creat
 */

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

    }

}