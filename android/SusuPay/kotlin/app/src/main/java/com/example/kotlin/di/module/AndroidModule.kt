package com.example.architecturaltemplate.di.module


import androidx.databinding.library.BuildConfig
import com.example.architecturaltemplate.network.RemoteDataSource
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class AndroidModule {

    /**
     * @author Peacemaker Otoo
     * We create a class called AndroidModule.java and annotate it with @Module to signal to
     * Dagger to search within the available methods for possible instance providers.
     * The methods that will actually expose available return types
     * should also be annotated with the @Provides annotation.
     * The @Singleton annotation also signals to the Dagger compiler that the instance should be created only once in the application.
     * In the following example, we can specify SharedPreferences, Gson or Moshi, Cache, OkHttpClient, and Retrofit as the return types
     * that can be used as part of the dependency list.
     * */

    @Provides
    @Singleton
    fun provideRemoteDataSource(): RemoteDataSource = RemoteDataSource()

    @Provides
    @Singleton
    fun provideHttpLogging(): OkHttpClient {
        return OkHttpClient.Builder().also { client ->
            if(BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }
        }.build()
    }

}
