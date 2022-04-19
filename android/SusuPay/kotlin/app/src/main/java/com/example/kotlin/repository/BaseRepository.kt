package com.example.kotlin.repository

import com.example.kotlin.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


abstract class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())

            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Failures(
                            false,
                            throwable.code(),
                            throwable.message ?: "Http connection error",
                            throwable.response()?.errorBody()
                        )
                    }
                    else -> {
                        Resource.Failures(true, null, throwable.message ?: "connecting...")
                    }
                }
            } catch (e: HttpException) {
                Resource.Failures(false, errorMessage = e.message ?: "Something went wrong")
            } catch (e: SocketTimeoutException) {
                Resource.Failures(false, errorMessage = e.message ?: "connection error!")
            } catch (e: ConnectException) {
                Resource.Failures(false, errorMessage = e.message ?: "no internet access")
            } catch (e: UnknownHostException) {
                Resource.Failures(false, errorMessage = e.message ?: "connection error")
            }
        }

    }


    suspend fun <T> safeApiCall2(apiCall: suspend () -> T): T {
        return withContext(Dispatchers.IO) {
            apiCall.invoke()

        }
    }
}