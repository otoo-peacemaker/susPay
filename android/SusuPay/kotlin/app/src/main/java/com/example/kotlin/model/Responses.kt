package com.example.kotlin.model

data class LoginResponse(
    val authData: AuthData,
    val message: String,
    val status: String
)

data class RegisterResponse(
    val authData: AuthData,
    val message: String,
    val status: String
)

data class TokenResponse(
    val access_token: String?,
    val refresh_token: String?
)