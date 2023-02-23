package com.example.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.newsapi.databinding.ActivityDetailsBinding
import com.example.newsapi.databinding.ActivityDetailsTestBinding
import com.example.newsapi.model.Article

class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val article = intent.getParcelableExtra<Article>("article")
        if (article != null){
            binding.apply {
                title.text = article.title
                description.text = article.description
                author.text = article.author
                //url.text = article.url
                Glide.with(this@DetailsActivity)
                    .load(article.urlToImage)
                    .into(binding.image)
            }
        }
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }
}