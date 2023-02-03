package com.bimalghara.sharedtest.sharedUtils

/**
 * Created by BimalGhara
 */
sealed class FailureType {
    object Network : FailureType()
    object Timeout : FailureType()
    object Http : FailureType()
}
