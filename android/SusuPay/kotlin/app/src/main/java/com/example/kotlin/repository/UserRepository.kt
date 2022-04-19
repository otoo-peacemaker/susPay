package com.example.kotlin.repository

import com.example.kotlin.network.HttpRequest

class UserRepository(private val api: HttpRequest) : BaseRepository() {
    suspend fun login(body: HashMap<String, String>) = safeApiCall {
        api.login(body)
    }

    suspend fun register(body: HashMap<String, String>) = safeApiCall {
        api.register(body)
    }
}