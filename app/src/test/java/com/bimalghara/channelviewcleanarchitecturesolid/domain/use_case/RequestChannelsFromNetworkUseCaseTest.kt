package com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case

import com.bimalghara.channelviewcleanarchitecturesolid.MainCoroutineRule
import com.bimalghara.channelviewcleanarchitecturesolid.common.TestDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule

/**
 * Created by BimalGhara
 */

@ExperimentalCoroutinesApi
class RequestChannelsFromNetworkUseCaseTest {

    private val testDispatcher = TestDispatcherProvider()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule(testDispatcher.main)

    @Before
    fun setUp() {
    }
}