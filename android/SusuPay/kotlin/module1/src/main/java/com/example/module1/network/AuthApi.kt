package com.example.architecturaltemplate.network

import com.example.architecturaltemplate.dao.LoginResponse
import com.example.architecturaltemplate.dao.RegisterResponse
import com.example.architecturaltemplate.util.Constants
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

@POST(Constants.Url.login)
suspend fun login(@Body body: HashMap<String,String>) : LoginResponse

@POST(Constants.Url.register)
suspend fun register(@Body body: HashMap<String,String>) : RegisterResponse


}