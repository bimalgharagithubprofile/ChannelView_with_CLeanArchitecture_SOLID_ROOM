package com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case

import com.bimalghara.channelviewcleanarchitecturesolid.DataStatus
import com.bimalghara.channelviewcleanarchitecturesolid.FailureType
import com.bimalghara.channelviewcleanarchitecturesolid.MainCoroutineRule
import com.bimalghara.channelviewcleanarchitecturesolid.TestUtil.dataStatus
import com.bimalghara.channelviewcleanarchitecturesolid.TestUtil.failureType
import com.bimalghara.channelviewcleanarchitecturesolid.common.dispatcher.TestDispatcherProvider
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ERROR_NETWORK_ERROR
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ERROR_SOCKET_TIMEOUT
import com.bimalghara.channelviewcleanarchitecturesolid.data.repository.FakeChannelsRepository
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.ChannelsRepositorySource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import com.google.common.truth.Truth.assertThat

import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by BimalGhara
 */

@ExperimentalCoroutinesApi
class RequestChannelsFromNetworkUseCaseTest {

    private val testDispatcher = TestDispatcherProvider()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule(testDispatcher.main)

    private lateinit var fakeChannelsRepositorySource: ChannelsRepositorySource
    private lateinit var requestChannelsFromNetworkUseCase: RequestChannelsFromNetworkUseCase


    @Before
    fun setUp() {
        fakeChannelsRepositorySource = FakeChannelsRepository()
        requestChannelsFromNetworkUseCase = RequestChannelsFromNetworkUseCase(testDispatcher, fakeChannelsRepositorySource)
    }

    @Test
    fun `Request channel list expected empty`() = runTest {
        //Arrange
        dataStatus = DataStatus.EmptyResponse

        //Act
        val channels = requestChannelsFromNetworkUseCase().last()

        //Assert
        assertThat(channels.data).isEqualTo(0)
    }

    @Test
    fun `Request channel list expected Success with 2 records`() = runTest {
        //Arrange
        dataStatus = DataStatus.Success

        //Act
        val channels = requestChannelsFromNetworkUseCase().last()

        //Assert
        assertThat(channels.data).isEqualTo(2)
    }

    @Test
    fun `Request channel list expected Failure of Network`() = runTest {
        //Arrange
        dataStatus = DataStatus.Fail
        failureType = FailureType.Network

        //Act
        val channels = requestChannelsFromNetworkUseCase().last()

        //Assert
        assertThat(channels.error?.message).isEqualTo(ERROR_NETWORK_ERROR)
    }

    @Test
    fun `Request channel list expected Failure of Http 401`() = runTest {
        //Arrange
        dataStatus = DataStatus.Fail
        failureType = FailureType.Http

        //Act
        val channels = requestChannelsFromNetworkUseCase().last()

        //Assert
        assertThat(channels.error?.message).isEqualTo("401")
    }

    @Test
    fun `Request channel list expected Failure of Timeout`() = runTest {
        //Arrange
        dataStatus = DataStatus.Fail
        failureType = FailureType.Timeout

        //Act
        val channels = requestChannelsFromNetworkUseCase().last()

        //Assert
        assertThat(channels.error?.message).isEqualTo(ERROR_SOCKET_TIMEOUT)
    }

}