package com.example.countrieslist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.countrieslist.R
import com.example.countrieslist.model.Country
import com.example.countrieslist.util.getProgressDrawable
import com.example.countrieslist.util.loadImage
import kotlinx.android.synthetic.main.view_item.view.*

class CountriesListAdapter(var countriesList: ArrayList<Country>):
        RecyclerView.Adapter<CountriesListAdapter.CountryViewHolder>() {

    fun updateCountriesList(newCountriesList: List<Country>){
        countriesList.clear()
        countriesList.addAll(newCountriesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CountryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false)
            )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindViews(countriesList[position])
    }

    override fun getItemCount(): Int = countriesList.size

    class CountryViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val countryNameView = view.countryName
        private val capitalView = view.capitalName
        private val flagView = view.flagView

        fun bindViews(country: Country) {
            countryNameView.text = country.countryName
            capitalView.text = country.capital
            flagView.loadImage(country.flagId, getProgressDrawable(flagView.context))

        }
    }
}