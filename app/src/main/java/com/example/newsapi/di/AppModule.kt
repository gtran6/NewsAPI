package com.example.newsapi.di

import com.example.newsapi.repository.MainRepository
import com.example.newsapi.service.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(apiInterface: ApiInterface) : MainRepository = MainRepository(apiInterface)

    @Singleton
    @Provides
    fun provideRetrofitInstance() : ApiInterface {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}