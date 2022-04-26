package com.example.kotlin.network

import com.example.kotlin.model.LoginResponse
import com.example.kotlin.model.RegisterResponse
import com.example.kotlin.util.Constants
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthHttpRequest : BaseApi {
    @POST(Constants.Url.login)
    suspend fun login(@Body body: HashMap<String, String>): LoginResponse

    @POST(Constants.Url.register)
    suspend fun register(@Body body: HashMap<String, String>): RegisterResponse

}

interface UserApi : BaseApi {
    @POST("user")
    suspend fun loginUser(): LoginResponse

    @POST("/auth/registration/")
    suspend fun registerUser(): RegisterResponse
}


interface BaseApi {
    @POST("auth/login/")
    suspend fun logout(): ResponseBody
}
