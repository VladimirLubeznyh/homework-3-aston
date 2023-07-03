package ru.lyubeznyh.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var countries: RecyclerView
    private val adapter: CountriesListAdapter by lazy {
        CountriesListAdapter(randomCountries(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countries = findViewById(R.id.rvCountryList)
        countries.adapter = adapter
    }
}
