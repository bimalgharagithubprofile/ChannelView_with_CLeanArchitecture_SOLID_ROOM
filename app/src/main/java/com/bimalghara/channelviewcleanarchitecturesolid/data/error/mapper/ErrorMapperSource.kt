package com.bimalghara.channelviewcleanarchitecturesolid.data.error.mapper

import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ErrorDetails

interface ErrorMapperSource {
    //fun getErrorString(errorId: Int): String

    fun getErrorByCode(errorCode: String): ErrorDetails

    val errorsMap: Map<String, String>
}
