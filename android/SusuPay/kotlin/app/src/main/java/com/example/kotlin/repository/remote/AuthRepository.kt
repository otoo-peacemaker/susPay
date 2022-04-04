package com.example.kotlin.repository.remote
import com.example.kotlin.dao.AuthsResponse
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
abstract class AuthRepository @Inject constructor(): BaseRepository<>{

}