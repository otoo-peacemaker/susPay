/*
package com.example.kotlin.di

import android.content.Context
import com.example.kotlin.webservices.NetworkConnection
import com.example.kotlin.webservices.Endpoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BackendApiModule {
    //interceptor
    @Singleton
    @Provides
     fun interceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    //okClient
    @Singleton
    @Provides
    fun client(): OkHttpClient = OkHttpClient.Builder().apply {
        // this.addInterceptor(TokenInterceptor())
        this.addInterceptor(NetworkConnection())
        this.addInterceptor(interceptor())
        this.connectTimeout(1, TimeUnit.MINUTES)
        this.writeTimeout(1, TimeUnit.MINUTES)
        this.readTimeout(1, TimeUnit.MINUTES)
    }.build()

    //retrofit
    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Endpoints.devBaseURL)
            .client(client())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideContext(application: MainApplication): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MainApplication {
        return app as MainApplication
    }


}*/
