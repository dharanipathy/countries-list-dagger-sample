package com.example.countrieslist.model

import com.example.countrieslist.di.DaggerCountriesApiComponent
import javax.inject.Inject

class CountriesService {

    @Inject
    lateinit var api: CountriesApi

    init{
        DaggerCountriesApiComponent.create().inject(this)
    }

    fun getCountriesList() = api.getCountriesList()


}