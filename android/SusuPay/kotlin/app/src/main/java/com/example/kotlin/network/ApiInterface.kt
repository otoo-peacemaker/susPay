package com.example.kotlin.network

import com.example.kotlin.dao.Auths
import com.example.kotlin.dao.AuthsResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiInterface {
    //registration
    @Headers("Accept: application/json", "Content-Type:application/json")
    @POST(Endpoints.registration)
    fun registerUser(@Body info: Auths): Observable<AuthsResponse>

    //login
    @Headers("Accept:application/json", "Content-Type:application/json")
    @POST(Endpoints.login)
    fun loginUser(
        @Body params: Auths
    ): Observable<AuthsResponse>

    //resend Email
    @Headers("Accept:application/json", "Content-Type:application/json")
    @POST(Endpoints.resendEmail)
    fun resendEmail(@Body params: Auths): Observable<AuthsResponse>

    //number for reset password
    @Headers("Accept:application/json", "Content-Type:application/json")
    @POST(Endpoints.passwordResetMail)
    suspend fun resetPasswordNum(@Body params: Auths): Observable<AuthsResponse>

    //verify PasswordEmail
    @Headers("Accept:application/json", "Content-Type:application/json")
    @POST(Endpoints.verifyResetCode)
     fun verifyPasscode(@Body params: Auths): Observable<AuthsResponse>

    // create new password
    @Headers("Accept:application/json", "Content-Type:application/json")
    @POST(Endpoints.resetPassword)
    fun resetPassword(@Body params: Auths): Observable<AuthsResponse>


   /* //updating user profile
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json"
    )
    @PUT(Endpoints.updateUser)
    suspend fun updateUser(
        @Body params: UpdateUser,
    ): Call<UpdateUserResponse>

    @Multipart
    @PUT(Endpoints.profileImg)
    suspend fun profileImage(
        @Part file: MultipartBody.Part
    ): Call<UploadResponse>*/

}