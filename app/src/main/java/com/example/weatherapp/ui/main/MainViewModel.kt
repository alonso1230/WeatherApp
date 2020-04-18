package com.example.weatherapp.ui.main

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.api.Repository
import com.example.weatherapp.api.RequestParam
import com.example.weatherapp.base.BaseViewModel
import com.example.weatherapp.model.Resource
import com.example.weatherapp.model.response.FindResponse
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    private val findResponseLiveData = MutableLiveData<Resource<FindResponse>>()

    fun getFindResponseLiveData() = findResponseLiveData

    fun loadWeather(latitude: String, longitude: String) {
        repository.getFind(findResponseLiveData, RequestParam.getFindParam(latitude, longitude, 10))
    }

}