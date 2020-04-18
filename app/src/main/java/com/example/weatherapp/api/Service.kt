package com.example.weatherapp.api

import com.example.weatherapp.model.response.FindResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface Service {

    @GET(Url.FIND)
    suspend fun find(@QueryMap param: Map<String, String>): FindResponse

}