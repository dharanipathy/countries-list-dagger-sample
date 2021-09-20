package com.example.countrieslist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countrieslist.R
import com.example.countrieslist.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: CountryViewModel
    private val countriesListAdapter = CountriesListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = countriesListAdapter
        }
        viewModel.refresh()
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }
        observeViewModels()
    }

    private fun observeViewModels() {
        viewModel.countries.observe(this, Observer{countries ->
            countries?.let {
                recyclerView.visibility = View.VISIBLE
                countriesListAdapter.updateCountriesList(countries)
            }
        })

        viewModel.error.observe(this, Observer { error ->
            error?.let { errorText.visibility = if (error) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { loading ->
            loading?.let {
                progressBar.visibility = if (loading) View.VISIBLE else View.GONE
                if(loading) {
                    recyclerView.visibility = View.GONE
                    errorText.visibility = View.GONE
                }
            }
        })
    }
}