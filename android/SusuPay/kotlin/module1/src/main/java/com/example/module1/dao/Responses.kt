package com.example.architecturaltemplate.dao

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