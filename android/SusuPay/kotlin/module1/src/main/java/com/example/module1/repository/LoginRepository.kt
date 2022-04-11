package com.example.architecturaltemplate.repository

import com.example.architecturaltemplate.network.AuthApi
import com.triad.mvvmlearning.repository.BaseRepository

class LoginRepository(private val api: AuthApi) : BaseRepository(){
    suspend fun login(body: HashMap<String,String>) = safeApiCall {
        api.login(body)
    }
}