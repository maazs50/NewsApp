package com.tarang.newsapp.common

/*
* Used for network calls and maintain the state
* @T is the dataType a model class
* */
sealed class Resource<T>(val data:T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data,message)
    class Loading<T>(): Resource<T>()
}