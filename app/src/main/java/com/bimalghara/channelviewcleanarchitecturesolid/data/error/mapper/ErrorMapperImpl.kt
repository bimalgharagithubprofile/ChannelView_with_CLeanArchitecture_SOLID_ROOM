package com.bimalghara.channelviewcleanarchitecturesolid.data.error.mapper

import android.content.Context
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ErrorDetails
import com.bimalghara.channelviewcleanarchitecturesolid.R
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ERROR_NETWORK_ERROR
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ERROR_NO_INTERNET_CONNECTION
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ERROR_SOCKET_TIMEOUT
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ErrorMapperImpl @Inject constructor(@ApplicationContext val context: Context) :
    ErrorMapperSource {



    override fun getErrorByCode(errorCode: String): ErrorDetails {
        return ErrorDetails(code = errorCode, description = errorsMap.getValue(errorCode))
    }

    override val errorsMap: Map<String, String>
        get() = mapOf(
            Pair(ERROR_NO_INTERNET_CONNECTION, getErrorString(R.string.no_internet)),
            Pair(ERROR_NETWORK_ERROR, getErrorString(R.string.network_error)),
            Pair(ERROR_SOCKET_TIMEOUT, getErrorString(R.string.socket_timeout)),

        ).withDefault { "Oops! Something went wrong" }

    private fun getErrorString(errorId: Int): String {
        return context.getString(errorId)
    }
}