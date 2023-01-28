package com.bimalghara.channelviewcleanarchitecturesolid.data.repository

import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ErrorDetails
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.mapper.ErrorMapperSource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.ErrorDetailsSource
import javax.inject.Inject

/**
 * Created by BimalGhara
 */

class ErrorDetailsImpl @Inject constructor(private val errorMapperSource: ErrorMapperSource) : ErrorDetailsSource {

    override suspend fun getErrorDetails(cause: String): ErrorDetails {

        // 2 types of error:
        // 1 -> String-Resource  [ie: -7*** ]
        // 2 -> kotlin.Exception [ie: message as String]

        return if(cause.startsWith("-")){
            errorMapperSource.getErrorByCode(cause)
        } else {
            ErrorDetails(cause)
        }
    }

}