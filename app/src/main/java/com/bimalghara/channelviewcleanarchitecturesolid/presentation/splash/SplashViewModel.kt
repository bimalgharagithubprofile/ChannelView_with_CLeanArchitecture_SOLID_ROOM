package com.bimalghara.channelviewcleanarchitecturesolid.presentation.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case.RequestCategoriesFromNetworkUseCase
import com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case.RequestChannelsFromNetworkUseCase
import com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case.RequestEpisodesFromNetworkUseCase
import com.bimalghara.channelviewcleanarchitecturesolid.utils.NetworkConnectivitySource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by BimalGhara
 */

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val ioDispatcher: CoroutineContext,
    private val networkConnectivitySource: NetworkConnectivitySource,
    private val requestCategoriesFromNetworkUseCase: RequestCategoriesFromNetworkUseCase,
    private val requestChannelsFromNetworkUseCase: RequestChannelsFromNetworkUseCase,
    private val requestEpisodesFromNetworkUseCase: RequestEpisodesFromNetworkUseCase
) : ViewModel() {
    private val logTag = javaClass.simpleName

    private val _isLoadingLiveData = MutableLiveData(true)
    val isLoadingLiveData: LiveData<Boolean> get() = _isLoadingLiveData

    private var _categoriesJob: Job? = null
    @Volatile private var _isCategoriesJobCompleted = false
    private var _channelsJob: Job? = null
    @Volatile private var _isChannelsJobCompleted = false
    private var _episodesJob: Job? = null
    @Volatile private var _isEpisodesJobCompleted = false

    init {
        getNetworkStatus()
    }

    private fun getNetworkStatus() = viewModelScope.launch {
        val networkStatus = networkConnectivitySource.getStatus(ioDispatcher)
        Log.i(logTag, "network status: $networkStatus")
        if (networkStatus == NetworkConnectivitySource.Status.Available){
            downloadAllChannelsDataFromCloud()
        } else{
            _isLoadingLiveData.value = false
        }
    }


    private fun downloadAllChannelsDataFromCloud() {
        /* request for episodes */
        if (_episodesJob == null) {//create job once
            viewModelScope.launch {
                val result = requestEpisodesFromNetworkUseCase().last()
                _isEpisodesJobCompleted = true
                setIsLoading()
            }
        }

        /* request for channels */
        if(_channelsJob == null) {//create job once
            viewModelScope.launch {
                val result = requestChannelsFromNetworkUseCase().last()
                _isChannelsJobCompleted = true
                setIsLoading()
            }
        }

        /* request for categories */
        if(_categoriesJob == null) {//create job once
            viewModelScope.launch {
                val result = requestCategoriesFromNetworkUseCase().last()
                _isCategoriesJobCompleted = true
                setIsLoading()
            }
        }
    }

    private fun setIsLoading() {
        if(_isEpisodesJobCompleted && _isChannelsJobCompleted && _isCategoriesJobCompleted)
            _isLoadingLiveData.value = false
    }
}