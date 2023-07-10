package ru.lyubeznyh.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.lyubeznyh.recyclerview.databinding.ItemCountryBinding

class CountriesListAdapter(private val countries: List<Country>) : Adapter<CountriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountryBinding.inflate(inflater, parent, false)
        return CountriesViewHolder(binding)
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.bind(countries[position], position + 1)
    }
}

class CountriesViewHolder(private val binding: ItemCountryBinding) : ViewHolder(binding.root) {
    fun bind(item: Country, position: Int) {
        binding.run {
            tvCountryName.text = item.name
            ivFlagImage.setImageResource(item.flagImage)
            tvNumber.text = position.toString()
        }
    }
}
