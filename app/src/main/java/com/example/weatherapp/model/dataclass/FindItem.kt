package com.example.weatherapp.model.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FindItem(
    val id: Int?,
    val name: String?,
    val coord: Coord?,
    val main: Main?,
    val dt: Long?,
    val wind: Wind?,
    val sys: Sys?,
    val rain: Rain?,
    val clouds: Clouds?,
    val weather: List<Weather>?
) : Parcelable