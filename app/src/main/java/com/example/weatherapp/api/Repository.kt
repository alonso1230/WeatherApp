package com.example.weatherapp.api

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.Resource
import com.example.weatherapp.model.Status
import com.example.weatherapp.model.response.FindResponse
import kotlinx.coroutines.*
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(val service: Service) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

    private suspend fun <T> sendResult(
        liveData: MutableLiveData<Resource<T>>,
        successResult: T?,
        errorResult: Throwable?
    ) {
        withContext(Dispatchers.Main) {
            if (successResult != null) {
                liveData.value = Resource(Status.SUCCESS, successResult)
            } else {
                liveData.value = Resource(Status.ERROR, errorResult)
            }
        }
    }

    fun getFind(
        liveData: MutableLiveData<Resource<FindResponse>>,
        param: HashMap<String, String>
    ) {
        liveData.value = Resource(Status.LOADING)
        coroutineScope.launch {
            try {
                val result = service.find(param)
                sendResult(liveData, result, null)
            } catch (e: HttpException) {
                sendResult(liveData, null, e)
            } catch (e: Throwable) {
                sendResult(liveData, null, e)
            }
        }
    }

}