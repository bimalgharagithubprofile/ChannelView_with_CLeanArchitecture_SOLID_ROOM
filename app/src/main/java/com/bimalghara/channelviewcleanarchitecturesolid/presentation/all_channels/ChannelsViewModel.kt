package com.bimalghara.channelviewcleanarchitecturesolid.presentation.all_channels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.CustomException
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ERROR_NO_INTERNET_CONNECTION
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case.GetChannelsFromNetworkUseCase
import com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case.GetErrorDetailsUseCase
import com.bimalghara.channelviewcleanarchitecturesolid.presentation.base.BaseViewModel
import com.bimalghara.channelviewcleanarchitecturesolid.utils.NetworkConnectivitySource
import com.bimalghara.channelviewcleanarchitecturesolid.utils.ResourceWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by BimalGhara
 */

@HiltViewModel
class ChannelsViewModel @Inject constructor (
    private val networkConnectivitySource: NetworkConnectivitySource,
    errorDetailsUseCase: GetErrorDetailsUseCase,
    private val getChannelsFromNetworkUseCase: GetChannelsFromNetworkUseCase
) : BaseViewModel(errorDetailsUseCase) {
    private val logTag = javaClass.simpleName

    private val _networkConnectivityLiveData = MutableLiveData<NetworkConnectivitySource.Status>()
    val networkConnectivityLiveData: LiveData<NetworkConnectivitySource.Status> get() = _networkConnectivityLiveData

    private var _channelsJob: Job? = null
    private val _channelsLiveData = MutableLiveData<ResourceWrapper<List<ChannelEntity>>>()
    val channelsLiveData: LiveData<ResourceWrapper<List<ChannelEntity>>> get() = _channelsLiveData

    init {
        observeNetworkStatus()
    }

    private fun observeNetworkStatus() = viewModelScope.launch {
        networkConnectivitySource.observe().collectLatest {
            Log.i(logTag, "network status: $it")
            _networkConnectivityLiveData.value = it

            refreshChannelsList()
        }
    }

    fun refreshChannelsList() {
        if(networkConnectivityLiveData.value != NetworkConnectivitySource.Status.Available) {
            showError(CustomException(cause = ERROR_NO_INTERNET_CONNECTION))//just to notify user about no-internet

            //get cached data (if exists)

        } else {

            _channelsJob?.cancel()
            _channelsJob = getChannelsFromNetworkUseCase().onEach {
                when (it) {
                    is ResourceWrapper.Error -> showError(it.error)
                    else -> Unit
                }

                _channelsLiveData.value = it

            }.launchIn(viewModelScope)
        }
    }
}