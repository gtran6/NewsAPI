package com.example.newsapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapi.databinding.ItemNewsBinding
import com.example.newsapi.model.Article

class NewsAdapter(val list: List<Article>) : RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.itemView)
                .load(list[position].urlToImage)
                .into(holder.binding.image)
            author.text = list[position].author
            title.text = list[position].title
            description.text = list[position].description
        }
    }
}