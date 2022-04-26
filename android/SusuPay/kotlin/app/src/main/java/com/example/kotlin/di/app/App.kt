package com.example.kotlin.di.app

import android.app.Application
import com.example.kotlin.BuildConfig

class App : Application() {

    private fun initializeLeakDetection() {
        when {
            BuildConfig.DEBUG -> {
                //LeakCanary.install(this)
            }
        }
    }
}