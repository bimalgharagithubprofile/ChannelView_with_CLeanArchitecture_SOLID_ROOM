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
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by BimalGhara
 */

@HiltViewModel
class SectionsViewModel @Inject constructor(
    private val ioDispatcher: CoroutineContext,
    private val networkConnectivitySource: NetworkConnectivitySource,
    errorDetailsUseCase: GetErrorDetailsUseCase,
    private val getCategoriesFromLocalUseCase: GetCategoriesFromLocalUseCase,
    private val getChannelsFromLocalUseCase: GetChannelsFromLocalUseCase,
    private val getEpisodesFromLocalUseCase: GetEpisodesFromLocalUseCase,
    private val requestCategoriesFromNetworkUseCase: RequestCategoriesFromNetworkUseCase,
    private val requestChannelsFromNetworkUseCase: RequestChannelsFromNetworkUseCase,
    private val requestEpisodesFromNetworkUseCase: RequestEpisodesFromNetworkUseCase
) : BaseViewModel(errorDetailsUseCase) {
    private val logTag = javaClass.simpleName

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
        loadAllSectionsData()
    }

    private suspend fun getNetworkStatus(): NetworkConnectivitySource.Status {
        val result = networkConnectivitySource.getStatus(ioDispatcher)
        Log.i(logTag, "network status: $result")
        return result
    }

    private fun loadAllSectionsData() {
        getEpisodesDataFromCached()
        getChannelsDataFromCached()
        getCategoriesDataFromCached()
    }

    fun refreshContent() = viewModelScope.launch {
        val networkStatus = async { getNetworkStatus() }.await()

        if (networkStatus != NetworkConnectivitySource.Status.Available) {
            showError(CustomException(cause = ERROR_NO_INTERNET_CONNECTION))//just to notify user about no-internet
        } else {
            requestAllSectionsDataFromCloud()
        }
    }


    /*
    * load data from local database
    */
    private fun getCategoriesDataFromCached() {
        _categoriesLiveData.value = ResourceWrapper.Loading()
        getCategoriesFromLocalUseCase().onEach { newList ->
            if (newList.isNotEmpty()) {
                val completeList: MutableList<CategoryEntity> = arrayListOf()
                if (_categoriesLiveData.value?.data != null) {
                    val existingList = _categoriesLiveData.value!!.data!!.toMutableList()
                    existingList.removeAll(newList)
                    completeList.addAll(existingList.plus(newList).toSet().toList())
                } else {
                    completeList.addAll(newList.toSet().toList())
                }

                if (completeList.isEmpty()) {
                    val ex = CustomException(cause = ERROR_NO_RECORDS)
                    showError(ex)
                    _categoriesLiveData.value = ResourceWrapper.Error(ex)
                } else {
                    _categoriesLiveData.value =
                        ResourceWrapper.Success(data = completeList)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getChannelsDataFromCached() {
        _channelsLiveData.value = ResourceWrapper.Loading()
        getChannelsFromLocalUseCase().onEach { newList ->
            if (newList.isNotEmpty()) {

                val completeList: MutableList<ChannelEntity> = arrayListOf()
                if (_channelsLiveData.value?.data != null) {
                    val existingList = _channelsLiveData.value!!.data!!.toMutableList()
                    existingList.removeAll(newList)
                    completeList.addAll(existingList.plus(newList).toSet().toList())
                } else {
                    completeList.addAll(newList.toSet().toList())
                }

                if (completeList.isEmpty()) {
                    val ex = CustomException(cause = ERROR_NO_RECORDS)
                    showError(ex)
                    _channelsLiveData.value = ResourceWrapper.Error(ex)
                } else {
                    _channelsLiveData.value = ResourceWrapper.Success(data = completeList)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getEpisodesDataFromCached() {
        _episodesLiveData.value = ResourceWrapper.Loading()
        getEpisodesFromLocalUseCase().onEach { newList ->
            if (newList.isNotEmpty()) {

                val completeList: MutableList<EpisodeEntity> = arrayListOf()
                if (_episodesLiveData.value?.data != null) {
                    val existingList = _episodesLiveData.value!!.data!!.toMutableList()
                    existingList.removeAll(newList)
                    completeList.addAll(existingList.plus(newList).toSet().toList())
                } else {
                    completeList.addAll(newList.toSet().toList())
                }

                if (completeList.isEmpty()) {
                    val ex = CustomException(cause = ERROR_NO_RECORDS)
                    showError(ex)
                    _episodesLiveData.value = ResourceWrapper.Error(ex)
                } else {
                    _episodesLiveData.value =
                        ResourceWrapper.Success(data = completeList)
                }
            }
        }.launchIn(viewModelScope)
    }


    /*
    * download latest data from network
    */
    private fun requestAllSectionsDataFromCloud() {
        /* request for episodes */
        _episodesJob?.cancel()//to prevent creating duplicate flow, fun is called multiple times
        _episodesJob = requestEpisodesFromNetworkUseCase().onEach {
            when (it) {
                is ResourceWrapper.Error -> showError(it.error)
                else -> Unit
            }
        }.launchIn(viewModelScope)

        /* request for channels */
        _channelsJob?.cancel()//to prevent creating duplicate flow, fun is called multiple times
        _channelsJob = requestChannelsFromNetworkUseCase().onEach {
            when (it) {
                is ResourceWrapper.Error -> showError(it.error)
                else -> Unit
            }
        }.launchIn(viewModelScope)

        /* request for categories */
        _categoriesJob?.cancel()//to prevent creating duplicate flow, fun is called multiple times
        _categoriesJob = requestCategoriesFromNetworkUseCase().onEach {
            when (it) {
                is ResourceWrapper.Error -> showError(it.error)
                else -> Unit
            }
        }.launchIn(viewModelScope)
    }
}