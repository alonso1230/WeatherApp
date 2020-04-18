package com.example.weatherapp.model.response

import android.os.Parcelable
import com.example.weatherapp.model.dataclass.FindItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FindResponse(
    val message: String?,
    val cod: String?,
    val count: Int?,
    val list: List<FindItem>?
) : Parcelable