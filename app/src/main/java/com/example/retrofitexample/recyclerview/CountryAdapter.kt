package com.example.retrofitexample.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitexample.R
import com.example.retrofitexample.data.Country

class CountryAdapter(private val context: Context) :
    ListAdapter<Country, CountryAdapter.CountryViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country: Country = getItem(position)
        Glide.with(context)
            .load(country.flags[1])
            .into(holder.countryFlag)
        holder.countryName.text = country.name
        holder.countryPopulation.text = country.population.toString()
    }

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val  countryName: TextView = itemView.findViewById(R.id.nameTextView)
        val countryPopulation: TextView = itemView.findViewById(R.id.populationTextView)
        val countryFlag: ImageView = itemView.findViewById(R.id.flagImageView)
    }
}

class DiffCallback :
    DiffUtil.ItemCallback<Country>() {

    override fun areItemsTheSame(
        oldItem: Country,
        newItem: Country
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: Country,
        newItem: Country
    ): Boolean {
        return oldItem == newItem
    }
}


