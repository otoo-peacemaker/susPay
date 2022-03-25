package com.example.kotlin.repository.remote

import android.util.Log
import com.example.kotlin.dao.Auths
import com.example.kotlin.dao.AuthsResponse
import com.example.kotlin.webservices.ApiInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HttpRequest(private val networkCall: ApiInterface) : AuthRepository {
    override fun login(userBody: Auths): Observable<AuthsResponse> {
        return networkCall.loginUser(userBody).subscribeOn(Schedulers.io())
            .doOnNext {
                Log.i("login", it.data.toString()) }
            .observeOn(AndroidSchedulers.mainThread())
        }

        override fun lookUp(userBody: Auths): Observable<AuthsResponse> {
            return networkCall.registerUser(userBody).subscribeOn(Schedulers.io()).doOnNext {
                Log.i("LookUp", it.data.toString())
            }.observeOn(AndroidSchedulers.mainThread())
        }
}



