package com.bimalghara.channelviewcleanarchitecturesolid.presentation.all_channels.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bimalghara.channelviewcleanarchitecturesolid.R
import com.bimalghara.channelviewcleanarchitecturesolid.databinding.ItemCategoriesWrapperBinding
import com.bimalghara.channelviewcleanarchitecturesolid.databinding.ItemChannelsWrapperBinding
import com.bimalghara.channelviewcleanarchitecturesolid.databinding.ItemEpisodesWrapperBinding
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.categories.CategoryEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.episodes.EpisodeEntity
import com.bimalghara.channelviewcleanarchitecturesolid.utils.loadImage
import com.bimalghara.channelviewcleanarchitecturesolid.utils.toGone
import com.bimalghara.channelviewcleanarchitecturesolid.utils.toVisible
import okhttp3.internal.toImmutableList

/**
 * Created by BimalGhara
 */

class AllSectionsAdapter(
    val context: Context
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val _episodes: MutableList<EpisodeEntity> = arrayListOf()
    private val _categories: MutableList<CategoryEntity> = arrayListOf()

    fun setEpisodes(episodes: List<EpisodeEntity>) {
        _episodes.clear()
        _episodes.addAll(episodes)
        notifyItemChanged(0)
    }
    fun setCategories(categories: List<CategoryEntity>) {
        _categories.clear()
        _categories.addAll(categories)

        notifyItemChanged(differ.currentList.size + 1)
    }

    private val differCallback = object : DiffUtil.ItemCallback<ChannelEntity>(){
        override fun areItemsTheSame(oldItem: ChannelEntity, newItem: ChannelEntity): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ChannelEntity, newItem: ChannelEntity): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    private val TYPE_HEADER = 0
    private val TYPE_FOOTER = 1
    private val TYPE_ITEMS = 2

    inner class CategoriesWrapperViewHolder(val binding: ItemCategoriesWrapperBinding): RecyclerView.ViewHolder(binding.root)
    inner class ChannelsWrapperViewHolder(val binding: ItemChannelsWrapperBinding): RecyclerView.ViewHolder(binding.root)
    inner class EpisodesWrapperViewHolder(val binding: ItemEpisodesWrapperBinding): RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = ItemEpisodesWrapperBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                EpisodesWrapperViewHolder(binding)
            }
            TYPE_FOOTER -> {
                val binding = ItemCategoriesWrapperBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CategoriesWrapperViewHolder(binding)
            }
            else -> {
                val binding = ItemChannelsWrapperBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ChannelsWrapperViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is EpisodesWrapperViewHolder -> {

                if(_episodes.isEmpty())
                    holder.binding.tvHeaderEpisodes.toGone()
                else
                    holder.binding.tvHeaderEpisodes.toVisible()

                //EpisodesMediaRecyclerview
                val episodesMediaAdapter = EpisodesMediaAdapter(context)
                holder.binding.rvEpisodes.apply {
                    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    this.adapter = episodesMediaAdapter
                }
                episodesMediaAdapter.differ.submitList(_episodes.toImmutableList())
            }
            is CategoriesWrapperViewHolder -> {
                if(_categories.isEmpty())
                    holder.binding.tvHeaderCategories.toGone()
                else
                    holder.binding.tvHeaderCategories.toVisible()

                //CategoriesRecyclerview
                val categoriesAdapter = CategoriesAdapter(context)
                holder.binding.rvCategories.apply {
                    this.adapter = categoriesAdapter
                }
                categoriesAdapter.differ.submitList(_categories.toImmutableList())
            }
            is ChannelsWrapperViewHolder -> {
                val itemChannel = differ.currentList[position-1]

                //thumbnail
                if(itemChannel.iconAsset.isNotEmpty())
                    holder.binding.ivHeaderChannelThumbnail.loadImage(itemChannel.iconAsset)
                else
                    holder.binding.ivHeaderChannelThumbnail.loadImage(R.mipmap.ic_logo_round)

                //text
                holder.binding.tvHeaderChannelName.text = itemChannel.title
                if(itemChannel.channelMedia.size > 1)
                    holder.binding.tvHeaderChannelMediaCount.text = context.getString(R.string.episodes, "${itemChannel.channelMedia.size}")
                else
                    holder.binding.tvHeaderChannelMediaCount.text = context.getString(R.string.episode, "${itemChannel.channelMedia.size}")

                //ChannelMediaRecyclerview
                val channelMediaAdapter = ChannelMediaAdapter(context)
                holder.binding.rvChannels.apply {
                    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    this.adapter = channelMediaAdapter
                }
                channelMediaAdapter.differ.submitList(itemChannel.channelMedia)
            }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size + 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                TYPE_HEADER
            }
            differ.currentList.size + 1 -> {
                TYPE_FOOTER
            }
            else -> {
                TYPE_ITEMS
            }
        }
    }


}