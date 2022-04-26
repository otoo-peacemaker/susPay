package com.example.kotlin.di.module

import android.content.Context
import com.example.kotlin.network.AuthHttpRequest
import com.example.kotlin.network.UserApi
import com.example.kotlin.repository.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAuthApi(
        remoteDataSource: RemoteDataSource.RemoteDataSource,
        @ApplicationContext context: Context
    ): AuthHttpRequest {
        return remoteDataSource.buildApiServices(AuthHttpRequest::class.java, context)
    }

    @Singleton
    @Provides
    fun provideUserApi(
        remoteDataSource: RemoteDataSource.RemoteDataSource,
        @ApplicationContext context: Context
    ): UserApi {
        return remoteDataSource.buildApiServices(UserApi::class.java, context)
    }
}