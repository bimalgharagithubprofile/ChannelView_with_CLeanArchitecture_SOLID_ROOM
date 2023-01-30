package com.bimalghara.channelviewcleanarchitecturesolid.presentation.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case.GetCategoriesFromNetworkUseCase
import com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case.GetChannelsFromNetworkUseCase
import com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case.GetEpisodesFromNetworkUseCase
import com.bimalghara.channelviewcleanarchitecturesolid.utils.NetworkConnectivitySource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by BimalGhara
 */

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val networkConnectivitySource: NetworkConnectivitySource,
    private val getCategoriesFromNetworkUseCase: GetCategoriesFromNetworkUseCase,
    private val getChannelsFromNetworkUseCase: GetChannelsFromNetworkUseCase,
    private val getEpisodesFromNetworkUseCase: GetEpisodesFromNetworkUseCase
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
        observeNetworkStatus()
    }

    private fun observeNetworkStatus() = viewModelScope.launch {
        networkConnectivitySource.observe().collectLatest {
            Log.i(logTag, "network status: $it")
            if (it == NetworkConnectivitySource.Status.Available){
                downloadAllChannelsDataFromCloud()
            }
        }
    }


    private fun downloadAllChannelsDataFromCloud() {
        /* request for episodes */
        if (_episodesJob == null) {//create job once
            viewModelScope.launch {
                val result = getEpisodesFromNetworkUseCase().last()
                _isEpisodesJobCompleted = true
                setIsLoading()
            }
        }

        /* request for channels */
        if(_channelsJob == null) {//create job once
            viewModelScope.launch {
                val result = getChannelsFromNetworkUseCase().last()
                _isChannelsJobCompleted = true
                setIsLoading()
            }
        }

        /* request for categories */
        if(_categoriesJob == null) {//create job once
            viewModelScope.launch {
                val result = getCategoriesFromNetworkUseCase().last()
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