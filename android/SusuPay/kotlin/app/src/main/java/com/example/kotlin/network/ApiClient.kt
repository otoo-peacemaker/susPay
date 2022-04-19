package com.example.kotlin.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    //interceptor
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    //okClient
    private fun client(): OkHttpClient = OkHttpClient.Builder().apply {
       // this.addInterceptor(TokenInterceptor())
        this.addInterceptor(NetworkConnection())
        this.addInterceptor(interceptor)
        this.connectTimeout(1, TimeUnit.MINUTES)
        this.writeTimeout(1, TimeUnit.MINUTES)
        this.readTimeout(1, TimeUnit.MINUTES)
    }.build()

    //retrofit

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Endpoints.devBaseURL)
            .client(client())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}