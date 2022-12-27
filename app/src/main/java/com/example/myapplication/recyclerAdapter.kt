package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class recyclerAdapter(val newsList: List<Article>) :RecyclerView.Adapter<recyclerAdapter.viewHolder>() {
    inner class viewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var uiTvNewsTitle = itemView.findViewById<TextView>(R.id.uiTvNewsTitle)
        var uiTvNewsName = itemView.findViewById<TextView>(R.id.uiTvNewsName)
        val uiIvNewsImage = itemView.findViewById<ImageView>(R.id.uiIvNewsImage)
        val uiTvNewsDescription = itemView.findViewById<TextView>(R.id.uiTvNewsDescription)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout,parent,false))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val itemsPosition = newsList[position]
        holder.uiTvNewsTitle.text =itemsPosition.title
        holder.uiTvNewsName.text = itemsPosition.author
//        holder.uiIvNewsImage.setImageDrawable(itemsPosition.urlToImage)
        holder.uiTvNewsDescription.text = itemsPosition.description

    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}