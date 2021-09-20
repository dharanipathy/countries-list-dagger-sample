package com.example.countrieslist.di

import com.example.countrieslist.model.CountriesApi
import com.example.countrieslist.model.CountriesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class CountriesApiModule{

    @Provides
    fun provideCountriesApi(): CountriesApi {
        return Retrofit
                .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }

    @Provides
    fun provideCountriesService(): CountriesService {
        return CountriesService()
    }

    companion object{
        private const val baseUrl = "https://raw.githubusercontent.com"
    }
}