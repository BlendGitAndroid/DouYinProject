package com.blend.douyinproject.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blend.douyinproject.R


class LikeAdapter : RecyclerView.Adapter<LikeAdapter.CommentHolder>() {

    private val dataList = ServerData.fetchData()


    class CommentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView.findViewById(R.id.avatar_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.like_item, parent, false)
        return CommentHolder(view)
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        dataList[position].apply {
            holder.text.text = name
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}