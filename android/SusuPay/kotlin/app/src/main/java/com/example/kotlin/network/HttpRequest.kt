package com.example.kotlin.network

import com.example.kotlin.model.LoginResponse
import com.example.kotlin.model.RegisterResponse
import com.example.kotlin.model.TokenResponse
import com.example.kotlin.util.Constants
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**Interface for user authentications*/
interface AuthHttpRequest : BaseApi {
    @POST(Endpoints.login)
    suspend fun login(@Body body: HashMap<String, String>): LoginResponse

    @POST(Endpoints.registration)
    suspend fun register(@Body body: HashMap<String, String>): RegisterResponse

}

interface TokenRefreshApi : BaseApi {
    @FormUrlEncoded
    @POST("auth/refresh-token/")
    suspend fun refreshAccessToken(
        @Field("refresh_token") refreshToken: String?
    ): TokenResponse
}


/**Interface for user related activities*/
interface UserApi : BaseApi {
    @POST("user")
    suspend fun loginUser(): LoginResponse

    @POST("/auth/registration/")
    suspend fun registerUser(): RegisterResponse
}


/**Interface to handle logout*/
interface BaseApi {
    @POST("auth/login/")
    suspend fun logout(): ResponseBody
}
