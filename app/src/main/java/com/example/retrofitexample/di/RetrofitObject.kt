package com.example.retrofitexample.di

import com.example.retrofitexample.network.CountryApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RetrofitObject {
    @Provides
    @Singleton
    fun retrofitProvider(): CountryApiService {
        val baseUrl = "https://restcountries.com/"
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(CountryApiService::class.java)
    }
}