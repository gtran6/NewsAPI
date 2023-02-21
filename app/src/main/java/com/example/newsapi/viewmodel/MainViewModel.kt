package com.example.newsapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapi.model.Article
import com.example.newsapi.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val mainRepository: MainRepository) : ViewModel() {
    val data = MutableLiveData<List<Article>>()

    fun getData(q : String, apiKey : String) {
        viewModelScope.launch {
            mainRepository.getNewsFromServer(q, apiKey).catch {
                Log.e("API", "get: ${it.localizedMessage}")
            }.collect { list ->
                data.value = list.articles
            }
        }
    }
}