package com.bimalghara.channelviewcleanarchitecturesolid

/**
 * Created by BimalGhara
 */
sealed class FailureType {
    object Network : FailureType()
    object Timeout : FailureType()
    object Http : FailureType()
}
