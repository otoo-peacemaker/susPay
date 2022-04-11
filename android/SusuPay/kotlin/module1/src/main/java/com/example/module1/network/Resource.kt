package com.example.architecturaltemplate.network

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<out T>(val value : T) : Resource<T>()
    data class Failure  (
        val isNetworkError: Boolean,
        val errorCode : Int?,
        val errorBody : ResponseBody?
    ): Resource<Nothing>()
     object Loading : Resource<Nothing>()
}

/**
 * @author Peacemaker Otoo
 * This class is responsible for communicating with the current state of network call to the UI
 * */

data class Res<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Res<T> {
            return Res(Status.SUCCESS, data, null)
        }
         fun <T> error(msg: String?, data: T?): Res<T> {
            return Res(Status.ERROR, data, msg)
        }
         fun <T> loading(data: T?): Res<T> {
            return Res(Status.LOADING, data, null)
        }
    }
}
