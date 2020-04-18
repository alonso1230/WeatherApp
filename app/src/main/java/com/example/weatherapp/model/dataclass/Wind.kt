package com.example.weatherapp.model.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wind(
    val speed: Double?,
    val deg: Int?
) : Parcelable