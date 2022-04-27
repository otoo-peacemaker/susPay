package com.example.kotlin.repository.remote

import android.content.Context
import androidx.databinding.library.BuildConfig
import com.example.kotlin.network.TokenAuthenticator
import com.example.kotlin.network.TokenRefreshApi
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject



    class RemoteDataSource @Inject constructor() {

        companion object {
            private const val BASE_URL = "https://backend.aegisrider.com"
        }

        fun <T> buildApiServices(
            api: Class<T>,
            context: Context
        ): T {
            val authenticator = TokenAuthenticator(context, buildTokenApi())
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getRetrofitClient(authenticator))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(api)
        }

        private fun buildTokenApi(): TokenRefreshApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getRetrofitClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TokenRefreshApi::class.java)
        }

        private fun getRetrofitClient(authenticator: Authenticator? = null): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.proceed(chain.request().newBuilder().also {
                        it.addHeader("Accept", "application/json")
                    }.build())
                }.also { client ->
                    authenticator?.let { client.authenticator(it) }
                    if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }
                }.build()
        }
    }