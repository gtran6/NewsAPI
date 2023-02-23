package com.example.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapi.adapter.NewsAdapter
import com.example.newsapi.databinding.ActivityMainBinding
import com.example.newsapi.extra.Events
import com.example.newsapi.model.Article
import com.example.newsapi.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val mainViewModel : MainViewModel by viewModels()
    var search : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.getData("bitcoin", "9850bc5968804dac9ba65c377b4220a2")

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search = query!!
                mainViewModel.getData(search, "9850bc5968804dac9ba65c377b4220a2")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        mainViewModel.data.observe(this, Observer {
            when (it) {
                is Events.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Events.Success -> {
                    it.let {
                        it.data?.let { it1 -> setAdapter(it1) }
                    }
                    binding.progressBar.visibility = View.GONE
                }
                is Events.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        })

    }

    private fun setAdapter(list: List<Article>) = binding.recyclerView.apply {
        adapter = NewsAdapter(list)
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}