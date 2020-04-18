package com.example.weatherapp.util.extention

import com.example.weatherapp.app.Constant
import kotlin.math.roundToInt

fun Double.toCelsius(): String {
    return (this - 273.15).roundToInt().toString() + " " + Constant.CELSIUS
}