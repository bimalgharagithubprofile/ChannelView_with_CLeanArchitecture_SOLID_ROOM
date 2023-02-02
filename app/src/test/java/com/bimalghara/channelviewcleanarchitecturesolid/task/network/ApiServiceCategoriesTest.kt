package com.bimalghara.channelviewcleanarchitecturesolid.task.network

import com.bimalghara.channelviewcleanarchitecturesolid.utils.Helper
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.CustomException
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ERROR_NETWORK_ERROR
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ERROR_SOCKET_TIMEOUT
import com.bimalghara.channelviewcleanarchitecturesolid.data.network.SafeApiRequest
import com.bimalghara.channelviewcleanarchitecturesolid.data.network.retrofit.service.ApiServiceCategories
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by BimalGhara
 */

@ExperimentalCoroutinesApi
class ApiServiceCategoriesTest : SafeApiRequest() {

    lateinit var mockWebServer: MockWebServer
    lateinit var apiServiceCategories: ApiServiceCategories

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        apiServiceCategories = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiServiceCategories::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }


    @Test
    fun `test Categories api expected Success with three records`() = runTest {
        //Arrange
        val mockerResponse = MockResponse()
        val content = Helper.readFileFromResource("/categories.json")
        mockerResponse.setResponseCode(200)
        mockerResponse.setBody(content)
        mockWebServer.enqueue(mockerResponse)

        //Act
        val response = apiRequest { apiServiceCategories.getCategoryList() }
        mockWebServer.takeRequest()

        //Assert
        assertThat(response.data.categories.size).isEqualTo(3)
    }

    @Test
    fun `test Categories api expected Error with 401`() = runTest {
        //Arrange
        val mockerResponse = MockResponse()
        mockerResponse.setResponseCode(401)
        mockerResponse.setBody("Un-Authorize")
        mockWebServer.enqueue(mockerResponse)

        //Act
        try {
            val response = apiRequest { apiServiceCategories.getCategoryList() }
            mockWebServer.takeRequest()
        }catch (e: CustomException) {
            //Assert
            assertThat(e.message).isEqualTo("401")
        }
    }

    @Test
    fun `test Categories api expected Error socket timeout`() = runTest {
        //Arrange
        val mockerResponse = MockResponse()
        mockerResponse.setSocketPolicy(SocketPolicy.NO_RESPONSE)
        mockWebServer.enqueue(mockerResponse)

        //Act
        try {
            val response = apiRequest { apiServiceCategories.getCategoryList() }
            mockWebServer.takeRequest()
        }catch (e: CustomException) {
            //Assert
            assertThat(e.message).isEqualTo(ERROR_SOCKET_TIMEOUT)
        }
    }

    @Test
    fun `test Categories api expected Error network lost`() = runTest {
        //Arrange
        val mockerResponse = MockResponse()
        mockerResponse.setSocketPolicy(SocketPolicy.DISCONNECT_AT_START)
        mockWebServer.enqueue(mockerResponse)

        //Act
        try {
            val response = apiRequest { apiServiceCategories.getCategoryList() }
            mockWebServer.takeRequest()
        }catch (e: CustomException) {
            //Assert
            assertThat(e.message).isEqualTo(ERROR_NETWORK_ERROR)
        }
    }
}