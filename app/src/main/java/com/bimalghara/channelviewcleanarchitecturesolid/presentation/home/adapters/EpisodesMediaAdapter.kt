package com.bimalghara.channelviewcleanarchitecturesolid.presentation.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bimalghara.channelviewcleanarchitecturesolid.R
import com.bimalghara.channelviewcleanarchitecturesolid.databinding.ItemMediaBinding
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.episodes.EpisodeEntity
import com.bimalghara.channelviewcleanarchitecturesolid.utils.loadImage
import com.bimalghara.channelviewcleanarchitecturesolid.utils.toVisible

/**
 * Created by BimalGhara
 */

class EpisodesMediaAdapter(
    val context: Context
): RecyclerView.Adapter<EpisodesMediaAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<EpisodeEntity>(){
        override fun areItemsTheSame(oldItem: EpisodeEntity, newItem: EpisodeEntity): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: EpisodeEntity, newItem: EpisodeEntity): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    inner class ViewHolder(val binding: ItemMediaBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesMediaAdapter.ViewHolder {
        val binding = ItemMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodesMediaAdapter.ViewHolder, position: Int) {
        val item = differ.currentList[position]

        //thumbnail
        if (item.coverAsset.isNotEmpty()) {
            holder.binding.ivMediaThumbnail.post {
                holder.binding.ivMediaThumbnail.loadImage(item.coverAsset)
            }
        } else
            holder.binding.ivMediaThumbnail.loadImage(R.mipmap.ic_logo)

        //text
        holder.binding.tvMediaTitle.text = item.title
        holder.binding.tvMediaTail.text = item.channel
        holder.binding.tvMediaTail.toVisible()

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}