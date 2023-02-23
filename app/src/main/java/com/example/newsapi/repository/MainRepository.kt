package com.example.newsapi.repository

import com.example.newsapi.model.ApiResponse
import com.example.newsapi.service.ApiInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(val apiInterface: ApiInterface) {

    fun getNewsFromServer(q : String, sortBy: String, apiKey : String) : Flow<ApiResponse> = flow {
        emit(apiInterface.getNewsList(q, sortBy, apiKey))
    }
}