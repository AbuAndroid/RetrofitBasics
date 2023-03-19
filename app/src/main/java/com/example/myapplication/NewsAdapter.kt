package com.example.myapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.Models.Article


class NewsAdapter(
    private val newsList: MutableList<Article>,
    private val onItemClick: (Article)->Unit
    ) :RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsPosition = newsList[position]
        holder.uiTvNewsTitle?.text = itemsPosition.title
        holder.uiTvNewsName?.text= itemsPosition.author
        holder.uiIvNewsImage?.let {
            Glide.with(it)
                .load(itemsPosition.urlToImage)
                .apply(RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(holder.uiIvNewsImage)
        }
        holder.uiTvNewsDescription?.text = itemsPosition.description
        holder.uiTvNewsDate?.text = itemsPosition.publishedAt
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onNewsListChanged(newsList: List<Article>) {
        this.newsList.clear()
        this.newsList.addAll(newsList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val uiTvNewsTitle:TextView? = itemView.findViewById(R.id.uiTvNewsTitle)
        val uiTvNewsName:TextView? = itemView.findViewById(R.id.uiTvNewsName)
        val uiIvNewsImage: ImageView? = itemView.findViewById(R.id.uiIvNewsImage)
        val uiTvNewsDescription:TextView? = itemView.findViewById(R.id.uiTvNewsDescription)
        val uiTvNewsDate : TextView? = itemView.findViewById(R.id.uiTvNewsDate)

        init {
            itemView.setOnClickListener{
                onItemClick(newsList[adapterPosition])
            }
        }

    }
}