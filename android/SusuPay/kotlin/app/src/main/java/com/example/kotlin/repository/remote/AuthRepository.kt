package com.example.kotlin.repository.remote

import com.example.kotlin.dao.AuthsResponse
import javax.inject.Singleton

@Singleton
interface AuthRepository : BaseRepository<AuthsResponse>