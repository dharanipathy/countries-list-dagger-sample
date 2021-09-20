package com.example.countrieslist.di

import com.example.countrieslist.model.CountriesService
import com.example.countrieslist.viewmodel.CountryViewModel
import dagger.Component

@Component(modules= [CountriesApiModule::class])
interface CountriesApiComponent{
    fun inject(service: CountriesService)
    fun inject(viewModel: CountryViewModel)
}