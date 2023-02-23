package com.example.newsapi.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapi.DetailsActivity
import com.example.newsapi.databinding.ItemListBinding
import com.example.newsapi.databinding.ItemNewsBinding
import com.example.newsapi.model.Article

class NewsAdapter(val list: List<Article>) : RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
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
            //description.text = list[position].description
            //url.text = list[position].url

            newsLayout.setOnClickListener {
                val activity = holder.itemView.context as Activity
                val intent = Intent(activity, DetailsActivity::class.java)
                intent.putExtra("article", list[position])
                holder.itemView.context.startActivity(intent)
            }
        }
    }
}