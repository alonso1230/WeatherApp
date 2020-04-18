package com.example.weatherapp.api

object RequestParam {

    fun getFindParam(lat: String, lon: String, cnt: Int?): HashMap<String, String> {
        val hashMap = HashMap<String, String>()
        hashMap[RequestField.LAT] = lat
        hashMap[RequestField.LON] = lon
        cnt?.let { hashMap[RequestField.CNT] = it.toString() }
        return hashMap
    }

}