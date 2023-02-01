package com.bimalghara.channelviewcleanarchitecturesolid

/**
 * Created by BimalGhara
 */
sealed class DataStatus {
    object EmptyResponse : DataStatus()
    object Success : DataStatus()
    object Fail : DataStatus()
}
