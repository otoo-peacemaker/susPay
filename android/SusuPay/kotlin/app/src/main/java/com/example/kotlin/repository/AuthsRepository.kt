package com.example.kotlin.repository

import com.example.kotlin.repository.BaseRepository

class AuthsRepository(private val api: AuthApi) : BaseRepository(){
    suspend fun login(body: HashMap<String,String>) = safeApiCall {
        api.login(body)
    }
}