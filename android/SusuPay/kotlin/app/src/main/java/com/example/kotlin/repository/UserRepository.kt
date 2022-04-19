package com.example.kotlin.repository

import com.example.kotlin.di.Scope
import com.example.kotlin.network.HttpRequest
import javax.inject.Inject

@Scope
class UserRepository @Inject constructor(private val api: HttpRequest) : BaseRepository() {
    suspend fun login(body: HashMap<String, String>) = safeApiCall {
        api.login(body)
    }

    suspend fun register(body: HashMap<String, String>) = safeApiCall {
        api.register(body)
    }
}