package com.example.weatherapp.model.dataclass

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rain(
    @SerializedName("1h")
    val oneH: Double?
) : Parcelable