package com.bimalghara.channelviewcleanarchitecturesolid.presentation.all_channels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.CustomException
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ERROR_NO_INTERNET_CONNECTION
import com.bimalghara.channelviewcleanarchitecturesolid.data.error.ERROR_NO_RECORDS
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.categories.CategoryEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.episodes.EpisodeEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case.*
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
    private val getCategoriesFromNetworkUseCase: GetCategoriesFromNetworkUseCase,
    private val getCategoriesFromLocalUseCase: GetCategoriesFromLocalUseCase,
    private val getChannelsFromNetworkUseCase: GetChannelsFromNetworkUseCase,
    private val getChannelsFromLocalUseCase: GetChannelsFromLocalUseCase,
    private val getEpisodesFromNetworkUseCase: GetEpisodesFromNetworkUseCase,
    private val getEpisodesFromLocalUseCase: GetEpisodesFromLocalUseCase
) : BaseViewModel(errorDetailsUseCase) {
    private val logTag = javaClass.simpleName

    private val _networkConnectivityLiveData = MutableLiveData<NetworkConnectivitySource.Status>()
    val networkConnectivityLiveData: LiveData<NetworkConnectivitySource.Status> get() = _networkConnectivityLiveData

    private var _categoriesJob: Job? = null
    private val _categoriesLiveData = MutableLiveData<ResourceWrapper<List<CategoryEntity>>>()
    val categoriesLiveData: LiveData<ResourceWrapper<List<CategoryEntity>>> get() = _categoriesLiveData

    private var _channelsJob: Job? = null
    private val _channelsLiveData = MutableLiveData<ResourceWrapper<List<ChannelEntity>>>()
    val channelsLiveData: LiveData<ResourceWrapper<List<ChannelEntity>>> get() = _channelsLiveData

    private var _episodesJob: Job? = null
    private val _episodesLiveData = MutableLiveData<ResourceWrapper<List<EpisodeEntity>>>()
    val episodesLiveData: LiveData<ResourceWrapper<List<EpisodeEntity>>> get() = _episodesLiveData

    init {
        observeNetworkStatus()
    }

    private fun observeNetworkStatus() = viewModelScope.launch {
        networkConnectivitySource.observe().collectLatest {
            Log.i(logTag, "network status: $it")
            _networkConnectivityLiveData.value = it

            refreshContent()
        }
    }

    fun refreshContent() {
        if(networkConnectivityLiveData.value != NetworkConnectivitySource.Status.Available) {
            showError(CustomException(cause = ERROR_NO_INTERNET_CONNECTION))//just to notify user about no-internet

            //get cached data (if exists)
            fetchCategoriesDataFromCached()
            fetchChannelsDataFromCached()
            fetchEpisodesDataFromCached()
        } else {
            fetchCategoriesDataFromCloud()
            fetchChannelsDataFromCloud()
            fetchEpisodesDataFromCloud()
        }
    }

    private fun fetchCategoriesDataFromCached() {
        _categoriesLiveData.value = ResourceWrapper.Loading()
        getCategoriesFromLocalUseCase().onEach {
            val appendedWithPreviousListOnEachEmission = categoriesLiveData.value?.data?.plus(it)
            if(appendedWithPreviousListOnEachEmission?.size == 0){
                val ex = CustomException(cause = ERROR_NO_RECORDS)
                showError(ex)
                _categoriesLiveData.value = ResourceWrapper.Error(ex)
            } else {
                _categoriesLiveData.value = ResourceWrapper.Success(data = appendedWithPreviousListOnEachEmission)
            }
        }
    }
    private fun fetchCategoriesDataFromCloud() {
        _categoriesJob?.cancel()//to prevent creating duplicate flow, fun is called multiple times
        _categoriesJob = getCategoriesFromNetworkUseCase().onEach {
            when (it) {
                is ResourceWrapper.Error -> showError(it.error)
                else -> Unit
            }

            _categoriesLiveData.value = it

        }.launchIn(viewModelScope)
    }

    private fun fetchChannelsDataFromCached() {
        _channelsLiveData.value = ResourceWrapper.Loading()
        getChannelsFromLocalUseCase().onEach {
            val appendedWithPreviousListOnEachEmission = channelsLiveData.value?.data?.plus(it)
            if(appendedWithPreviousListOnEachEmission?.size == 0){
                val ex = CustomException(cause = ERROR_NO_RECORDS)
                showError(ex)
                _channelsLiveData.value = ResourceWrapper.Error(ex)
            } else {
                _channelsLiveData.value = ResourceWrapper.Success(data = appendedWithPreviousListOnEachEmission)
            }
        }
    }
    private fun fetchChannelsDataFromCloud() {
        _channelsJob?.cancel()//to prevent creating duplicate flow, fun is called multiple times
        _channelsJob = getChannelsFromNetworkUseCase().onEach {
            when (it) {
                is ResourceWrapper.Error -> showError(it.error)
                else -> Unit
            }

            _channelsLiveData.value = it

        }.launchIn(viewModelScope)
    }

    private fun fetchEpisodesDataFromCached() {
        _episodesLiveData.value = ResourceWrapper.Loading()
        getEpisodesFromLocalUseCase().onEach {
            val appendedWithPreviousListOnEachEmission = episodesLiveData.value?.data?.plus(it)
            if(appendedWithPreviousListOnEachEmission?.size == 0){
                val ex = CustomException(cause = ERROR_NO_RECORDS)
                showError(ex)
                _episodesLiveData.value = ResourceWrapper.Error(ex)
            } else {
                _episodesLiveData.value = ResourceWrapper.Success(data = appendedWithPreviousListOnEachEmission)
            }
        }
    }
    private fun fetchEpisodesDataFromCloud() {
        _episodesJob?.cancel()//to prevent creating duplicate flow, fun is called multiple times
        _episodesJob = getEpisodesFromNetworkUseCase().onEach {
            when (it) {
                is ResourceWrapper.Error -> showError(it.error)
                else -> Unit
            }

            _episodesLiveData.value = it

        }.launchIn(viewModelScope)
    }
}