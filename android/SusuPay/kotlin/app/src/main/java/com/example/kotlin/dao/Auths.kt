package com.example.kotlin.dao

data class Auths(
    val phone: String?,
    val password: String?

)
data class AuthsResponse(
    val message: String?,
    val data: String?,
    val meta: String?
)