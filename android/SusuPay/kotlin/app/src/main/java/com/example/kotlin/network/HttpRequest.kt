package com.example.kotlin.network

import com.example.kotlin.dao.LoginResponse
import com.example.kotlin.dao.RegisterResponse
import com.example.kotlin.di.Scope
import com.example.kotlin.util.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.http.Body
import retrofit2.http.POST


interface HttpRequest {
    @POST(Constants.Url.login)
    suspend fun login(@Body body: HashMap<String, String>): LoginResponse

    @POST(Constants.Url.register)
    suspend fun register(@Body body: HashMap<String, String>): RegisterResponse

}
