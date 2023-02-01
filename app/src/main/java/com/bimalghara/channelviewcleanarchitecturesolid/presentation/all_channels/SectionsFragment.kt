package com.bimalghara.channelviewcleanarchitecturesolid.presentation.all_channels

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bimalghara.channelviewcleanarchitecturesolid.R
import com.bimalghara.channelviewcleanarchitecturesolid.databinding.FragmentSectionsBinding
import com.bimalghara.channelviewcleanarchitecturesolid.presentation.all_channels.adapters.AllSectionsAdapter
import com.bimalghara.channelviewcleanarchitecturesolid.presentation.base.BaseFragment
import com.bimalghara.channelviewcleanarchitecturesolid.utils.*
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by BimalGhara
 */

@AndroidEntryPoint
class SectionsFragment : BaseFragment<FragmentSectionsBinding>(),
    SwipeRefreshLayout.OnRefreshListener {
    private val logTag = javaClass.simpleName

    private val sectionsViewModel: SectionsViewModel by viewModels()

    private lateinit var allSectionsAdapter: AllSectionsAdapter


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSectionsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAllSectionsRecyclerview()

        binding.swipeContainer.setOnRefreshListener(this)
    }

    private fun setupAllSectionsRecyclerview() {
        allSectionsAdapter = AllSectionsAdapter(requireContext())
        binding.rvAllSections.apply {
            this.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = allSectionsAdapter
            this.addItemDecoration(RecyclerViewItemDecoration(requireContext(), R.drawable.divider))
        }
    }

    override fun observeViewModel() {
        observeError(sectionsViewModel.errorSingleEvent)

        observe(sectionsViewModel.episodesLiveData) {
            Log.d(logTag, "observe episodesLiveData | $it")
            when (it) {
                is ResourceWrapper.Loading -> {
                }
                is ResourceWrapper.Success -> {
                    if(!it.data.isNullOrEmpty()) {
                        allSectionsAdapter.setEpisodes(it.data)
                        binding.shimmer.toGone()
                        binding.rvAllSections.toVisible()
                    }
                }
                else -> {
                    binding.shimmer.toGone()
                }
            }
        }

        observe(sectionsViewModel.channelsLiveData) {
            Log.d(logTag, "observe channelsLiveData | $it")
            when (it) {
                is ResourceWrapper.Loading -> {
                }
                is ResourceWrapper.Success -> {
                    if(!it.data.isNullOrEmpty()) {
                        allSectionsAdapter.differ.submitList(it.data)
                        binding.shimmer.toGone()
                        binding.rvAllSections.toVisible()
                    }
                }
                else -> {
                    binding.shimmer.toGone()
                }
            }
        }

        observe(sectionsViewModel.categoriesLiveData) {
            Log.d(logTag, "observe categoriesLiveData | $it")
            when (it) {
                is ResourceWrapper.Success -> {
                    if(!it.data.isNullOrEmpty()) {
                        allSectionsAdapter.setCategories(it.data)
                    }
                }
                else -> Unit
            }
        }
    }

    override fun onRefresh() {
        sectionsViewModel.refreshContent()
        binding.swipeContainer.isRefreshing = false
    }


}