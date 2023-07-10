package ru.lyubeznyh.recyclerview

import android.content.Context
import kotlin.random.Random


fun randomCountries(context: Context): List<Country> {
    val flagsResId = listOf(
        R.drawable.br,
        R.drawable.ca,
        R.drawable.de,
        R.drawable.ru,
        R.drawable.uk,
    )
    val countriesNameResId = listOf(
        R.string.brazil,
        R.string.canada,
        R.string.germany,
        R.string.russian_federation,
        R.string.great_britain,
    )

    val list = mutableListOf<Country>()
    repeat(30) {
        val randomIndex = Random.nextInt(0, 5)
        list.add(
            Country(
                it,
                context.getString(countriesNameResId[randomIndex]),
                flagsResId[randomIndex]
            )
        )
    }
    return list
}
