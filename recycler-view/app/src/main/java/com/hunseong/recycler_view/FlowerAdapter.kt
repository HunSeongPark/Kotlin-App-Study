package com.hunseong.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class FlowerAdapter(private val onClick: (Flower) -> Unit) : ListAdapter<Flower, FlowerAdapter.ViewHolder>(FlowerDiffUtil) {

    class ViewHolder(itemView: View, onClick: (Flower) -> Unit) : RecyclerView.ViewHolder(itemView) {

        private val flowerTextView: TextView = itemView.findViewById(R.id.flower_tv)
        private var flower: Flower? = null

        init {
            itemView.setOnClickListener {
                flower?.let(onClick)
            }
        }

        fun bind(flower: Flower) {
            this.flower = flower
            flowerTextView.text = flower.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_flower, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        currentList[position]
    }
}

object FlowerDiffUtil : DiffUtil.ItemCallback<Flower>() {

    override fun areItemsTheSame(oldItem: Flower, newItem: Flower): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Flower, newItem: Flower): Boolean {
        return oldItem.id == newItem.id
    }
}