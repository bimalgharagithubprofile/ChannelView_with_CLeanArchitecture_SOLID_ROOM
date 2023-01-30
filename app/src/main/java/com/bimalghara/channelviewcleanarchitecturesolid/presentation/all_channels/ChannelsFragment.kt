package com.bimalghara.channelviewcleanarchitecturesolid.presentation.all_channels

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bimalghara.channelviewcleanarchitecturesolid.R
import com.bimalghara.channelviewcleanarchitecturesolid.databinding.FragmentChannelsBinding
import com.bimalghara.channelviewcleanarchitecturesolid.presentation.all_channels.adapters.AllChannelsAdapter
import com.bimalghara.channelviewcleanarchitecturesolid.presentation.base.BaseFragment
import com.bimalghara.channelviewcleanarchitecturesolid.utils.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by BimalGhara
 */

@AndroidEntryPoint
class ChannelsFragment : BaseFragment<FragmentChannelsBinding>() {
    private val logTag = javaClass.simpleName

    private val channelsViewModel: ChannelsViewModel by viewModels()

    private lateinit var allChannelsAdapter: AllChannelsAdapter


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentChannelsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAllChannelsRecyclerview()

    }

    private fun setupAllChannelsRecyclerview() {
        allChannelsAdapter = AllChannelsAdapter(requireContext())
        binding.rvAllChannels.apply {
            this.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = allChannelsAdapter
            this.addItemDecoration(RecyclerViewItemDecoration(requireContext(), R.drawable.divider))
        }
    }

    override fun observeViewModel() {
        observeError(channelsViewModel.errorSingleEvent)

        observe(channelsViewModel.networkConnectivityLiveData) {
            binding.root.showSnackBar("Network Status: $it", Snackbar.LENGTH_LONG)
        }

        observe(channelsViewModel.channelsLiveData) {
            Log.d(logTag, "observe channelsLiveData | $it")
            when (it) {
                is ResourceWrapper.Loading -> {
                }
                is ResourceWrapper.Success -> {
                    allChannelsAdapter.differ.submitList(it.data)
                    binding.shimmer.toGone()
                    binding.rvAllChannels.toVisible()
                }
                else -> {
                    binding.shimmer.toGone()
                }
            }
        }
    }


}