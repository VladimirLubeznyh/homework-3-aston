package ru.lyubeznyh.recyclerview

import androidx.annotation.DrawableRes

data class Country(
    val id:Int,
    val name:String,
    @DrawableRes
    val flagImage:Int
)
