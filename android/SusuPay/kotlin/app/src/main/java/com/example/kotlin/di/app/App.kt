package com.example.kotlin.di.app

import android.app.Application
import com.example.kotlin.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    private fun initializeLeakDetection() {
        when {
            BuildConfig.DEBUG -> {
                //LeakCanary.install(this)
            }
        }
    }
}