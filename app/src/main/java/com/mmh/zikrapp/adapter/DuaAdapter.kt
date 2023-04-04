package com.mmh.zikrapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mmh.zikrapp.R
import com.mmh.zikrapp.databinding.DuaItemBinding
import com.mmh.zikrapp.entity.DuaItem

class DuaAdapter(val onItemClick: (title: String) -> Unit) :
    ListAdapter<DuaItem, DuaAdapter.DuaItemViewHolder>(DuaItemDiffUtils()) {

    inner class DuaItemViewHolder(private val binding: DuaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dua: DuaItem) {
            with(binding) {
                titleTv.text = dua.title
                arabicTv.text = dua.arabic
                translitTv.text = dua.transliteration
                uzbekTv.text = dua.uzbek
                countTv.text = dua.quantity.toString()
                root.setOnClickListener {
                    onItemClick(dua.title)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DuaItemViewHolder {
        val binding = DuaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DuaItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DuaItemViewHolder, position: Int) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundResource(android.R.color.white)
        } else {
            holder.itemView.setBackgroundResource(R.color.light_gray)
        }
        holder.bind(getItem(position))
    }

    class DuaItemDiffUtils : DiffUtil.ItemCallback<DuaItem>() {
        override fun areItemsTheSame(oldItem: DuaItem, newItem: DuaItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DuaItem, newItem: DuaItem): Boolean {
            return oldItem == newItem
        }
    }
}
