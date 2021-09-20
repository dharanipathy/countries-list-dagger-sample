package com.example.countrieslist.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CountriesApi {

    @GET("DevTides/countries/master/countriesV2.json")
    fun getCountriesList(): Single<List<Country>>
}