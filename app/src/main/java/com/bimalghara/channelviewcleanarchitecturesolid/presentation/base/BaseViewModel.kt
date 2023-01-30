package com.bimalghara.channelviewcleanarchitecturesolid.presentation.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bimalghara.channelviewcleanarchitecturesolid.utils.SingleEvent
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.CustomException
import com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case.GetErrorDetailsUseCase
import com.bimalghara.channelviewcleanarchitecturesolid.utils.NetworkConnectivitySource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by BimalGhara
 */
abstract class BaseViewModel(
    private val networkConnectivitySource: NetworkConnectivitySource,
    private val errorDetailsUseCase: GetErrorDetailsUseCase,
) : ViewModel(){
    private val logTag = javaClass.simpleName

    private val _networkConnectivityLiveData = MutableLiveData<NetworkConnectivitySource.Status>()
    val networkConnectivityLiveData: LiveData<NetworkConnectivitySource.Status> get() = _networkConnectivityLiveData

    private val _errorSingleEvent = MutableLiveData<SingleEvent<Any>>()
    val errorSingleEvent: LiveData<SingleEvent<Any>> get() = _errorSingleEvent


    init {
        observeNetworkStatus()
    }

    private fun observeNetworkStatus() = viewModelScope.launch {
        networkConnectivitySource.observe().collectLatest {
            Log.i(logTag, "network status: $it")
            _networkConnectivityLiveData.value = it
        }
    }

    protected fun showError(errorDetails: CustomException?) = viewModelScope.launch {
        errorDetails?.message?.let {
            Log.e(logTag, "showing error: $it")
            val error = errorDetailsUseCase(it)
            _errorSingleEvent.value = SingleEvent(error.description)
        }
    }

}