package com.example.weatherapp.model.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    val id: Int?,
    val main: String?,
    val description: String?,
    val icon: String?
) : Parcelable