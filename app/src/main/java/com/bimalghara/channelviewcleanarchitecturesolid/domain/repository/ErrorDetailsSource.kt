package com.bimalghara.channelviewcleanarchitecturesolid.domain.repository

import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ErrorDetails

/**
 * Created by BimalGhara
 */

interface ErrorDetailsSource {
    suspend fun getErrorDetails(cause: String): ErrorDetails
}