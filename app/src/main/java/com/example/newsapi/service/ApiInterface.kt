package com.example.newsapi.service

import com.example.newsapi.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/v2/everything")
    suspend fun getNewsList(
        @Query("q") q : String,
        @Query("apiKey") apiKey : String
    ): ApiResponse
}