package com.hunseong.hilt.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hunseong.hilt.R
import com.hunseong.hilt.data.Log

class LogAdapter : ListAdapter<Log, LogAdapter.ViewHolder>(LogDiffUtil) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv = itemView.findViewById<TextView>(R.id.tv)

        fun bind(log: Log) {
            tv.text = log.msg
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_log, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object LogDiffUtil : DiffUtil.ItemCallback<Log>() {

    override fun areItemsTheSame(oldItem: Log, newItem: Log): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Log, newItem: Log): Boolean {
        return oldItem == newItem
    }

}