package com.bimalghara.channelviewcleanarchitecturesolid.presentation.all_channels.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bimalghara.channelviewcleanarchitecturesolid.databinding.ItemCategoryBinding
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.categories.CategoryEntity

/**
 * Created by BimalGhara
 */

class CategoriesAdapter(
    val context: Context
): RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<CategoryEntity>(){
        override fun areItemsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    inner class ViewHolder(val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapter.ViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.ViewHolder, position: Int) {
        val item = differ.currentList[position]

        //text
        holder.binding.tvCategoryName.text = item.name

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}