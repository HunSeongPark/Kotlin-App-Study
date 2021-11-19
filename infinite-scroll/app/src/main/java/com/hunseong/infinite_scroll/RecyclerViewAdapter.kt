package com.hunseong.infinite_scroll

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = ArrayList<String>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_LOADING -> LoadingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false))
            else -> ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> holder.bind(items[position])
            else -> Unit
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            " " -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_ITEM
        }
    }

    override fun getItemCount(): Int = items.size


    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.item_tv)

        fun bind(text: String) {
            title.text = text
        }
    }

    companion object {
        const val VIEW_TYPE_LOADING = 0
        const val VIEW_TYPE_ITEM = 1
    }

    fun setList(list: ArrayList<String>) {
        hideLoading()
        items.addAll(list)
    }

    fun showLoading() {
        items.add(" ")
        notifyItemInserted(items.lastIndex)
    }

    private fun hideLoading() {
        items.remove(" ")
    }
}