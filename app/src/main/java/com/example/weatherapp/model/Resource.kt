package com.example.weatherapp.model

data class Resource<T>(
    val status: Status,
    val data: T?,
    val throwable: Throwable?
) {
    constructor(status: Status) : this(status, null, null)
    constructor(status: Status, data: T?) : this(status, data, null)
    constructor(status: Status, error: Throwable?) : this(status, null, error)
}