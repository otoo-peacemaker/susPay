package com.example.kotlin.repository

import com.example.kotlin.network.AuthHttpRequest
import com.example.kotlin.repository.local.LocalDataSource
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthHttpRequest,
    private val localDataSource: LocalDataSource
) : BaseRepository(api) {
      suspend fun login(body: HashMap<String, String>) = safeApiCall {
        api.login(body)
    }

    suspend fun register(body: HashMap<String, String>) = safeApiCall {
        api.register(body)
    }

    suspend fun saveAccessTokens(accessToken: String, refreshToken: String) {
        localDataSource.saveAccessTokens(accessToken, refreshToken)
    }
}