package com.example.kotlin.network

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@SuppressLint("MissingPermission")
class NetworkConnection @Inject constructor() : Interceptor, ConnectivityManager.NetworkCallback() {
    private var online = false
    companion object{
         val connectivityManager: ConnectivityManager? = null
    }
    init {
        if (Build.VERSION.SDK_INT >= 24) {
            connectivityManager?.registerDefaultNetworkCallback(this)
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (Build.VERSION.SDK_INT < 24) {
            online = connectivityManager?.activeNetworkInfo?.isConnected ?: false
        }
        if (online) {
            return chain.proceed(chain.request())
        } else {
            throw IOException("Internet connection is unavailable")
        }
    }

    override fun onCapabilitiesChanged(
        network: Network,
        capabilities: NetworkCapabilities
    ) {
        online = capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}

