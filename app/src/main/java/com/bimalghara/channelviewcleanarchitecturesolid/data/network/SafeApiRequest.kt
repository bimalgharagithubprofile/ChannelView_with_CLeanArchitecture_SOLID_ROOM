package com.bimalghara.channelviewcleanarchitecturesolid.data.network

import android.util.Log
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.CustomException
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ERROR_NETWORK_ERROR
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ERROR_SOCKET_TIMEOUT
import retrofit2.HttpException
import java.net.SocketTimeoutException

/**
 * Created by BimalGhara
 */

abstract class SafeApiRequest {

    //returns DTO
    suspend fun<T> apiRequest(call: suspend () -> T): T {
        return try{
            call.invoke()
        }catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    throw CustomException(cause = throwable.code().toString())
                }
                is SocketTimeoutException -> {
                    throw CustomException(cause = ERROR_SOCKET_TIMEOUT)
                }
                else -> {
                    throw CustomException(cause = ERROR_NETWORK_ERROR)
                }
            }
        }
    }
}