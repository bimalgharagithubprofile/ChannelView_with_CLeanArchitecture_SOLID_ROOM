package com.bimalghara.channelviewcleanarchitecturesolid.presentation.all_channels.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bimalghara.channelviewcleanarchitecturesolid.databinding.ItemMediaBinding
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelMedia
import com.bimalghara.channelviewcleanarchitecturesolid.utils.toGone

/**
 * Created by BimalGhara
 */

class ChannelMediaAdapter(
    val context: Context
): RecyclerView.Adapter<ChannelMediaAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<ChannelMedia>(){
        override fun areItemsTheSame(oldItem: ChannelMedia, newItem: ChannelMedia): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ChannelMedia, newItem: ChannelMedia): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    inner class ViewHolder(val binding: ItemMediaBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelMediaAdapter.ViewHolder {
        val binding = ItemMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChannelMediaAdapter.ViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.binding.tvMediaTitle.text = item.title
        holder.binding.tvMediaTail.toGone()

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}