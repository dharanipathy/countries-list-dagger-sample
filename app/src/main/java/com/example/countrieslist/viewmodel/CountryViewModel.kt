package com.example.countrieslist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrieslist.di.DaggerCountriesApiComponent
import com.example.countrieslist.model.CountriesService
import com.example.countrieslist.model.Country
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CountryViewModel: ViewModel() {
    @Inject
    lateinit var countriesService: CountriesService

    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    init{
        DaggerCountriesApiComponent.create().inject(this)
    }

    fun refresh(){
        fetchCountryList()
    }

    private fun fetchCountryList() {
        loading.value = true
        disposable.add(
            countriesService.getCountriesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<List<Country>>(){
                override fun onSuccess(result: List<Country>?) {
                    result?.let {
                        countries.value = result!!
                        loading.value = false
                        error.value = false
                    }
                }

                override fun onError(e: Throwable?) {
                   loading.value= false
                    error.value = true
                }

            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}