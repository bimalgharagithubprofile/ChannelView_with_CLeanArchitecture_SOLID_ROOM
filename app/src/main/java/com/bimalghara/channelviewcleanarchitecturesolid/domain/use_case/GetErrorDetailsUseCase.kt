package com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case

import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ErrorDetails
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.ErrorDetailsSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by BimalGhara
 */

class GetErrorDetailsUseCase(private val errorDetailsSource: ErrorDetailsSource) {

    suspend operator fun invoke(cause: String): ErrorDetails = withContext(Dispatchers.IO){
        errorDetailsSource.getErrorDetails(cause)
    }
}