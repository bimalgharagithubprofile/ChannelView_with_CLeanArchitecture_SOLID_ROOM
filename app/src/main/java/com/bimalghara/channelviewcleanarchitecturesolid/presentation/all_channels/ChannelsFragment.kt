package com.bimalghara.channelviewcleanarchitecturesolid.presentation.all_channels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bimalghara.channelviewcleanarchitecturesolid.databinding.FragmentChannelsBinding
import com.bimalghara.channelviewcleanarchitecturesolid.presentation.base.BaseFragment
import com.bimalghara.channelviewcleanarchitecturesolid.utils.observe
import com.bimalghara.channelviewcleanarchitecturesolid.utils.showSnackBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by BimalGhara
 */

@AndroidEntryPoint
class ChannelsFragment : BaseFragment<FragmentChannelsBinding>() {
    private val logTag = javaClass.simpleName

    private val channelsViewModel: ChannelsViewModel by viewModels()


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentChannelsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //
    }

    override fun observeViewModel() {
        observeError(channelsViewModel.errorSingleEvent)

        observe(channelsViewModel.networkConnectivityLiveData) {
            binding.root.showSnackBar("Network Status: $it", Snackbar.LENGTH_LONG)
        }
    }


}