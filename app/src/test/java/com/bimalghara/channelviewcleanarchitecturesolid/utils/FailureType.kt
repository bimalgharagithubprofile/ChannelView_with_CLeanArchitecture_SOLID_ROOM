package com.bimalghara.channelviewcleanarchitecturesolid.utils

/**
 * Created by BimalGhara
 */
sealed class FailureType {
    object Network : FailureType()
    object Timeout : FailureType()
    object Http : FailureType()
}
