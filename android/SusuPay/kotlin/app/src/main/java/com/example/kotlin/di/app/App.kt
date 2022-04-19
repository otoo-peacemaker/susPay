package com.example.kotlin.di.app

import android.app.Application
import android.content.Context
import com.example.kotlin.BuildConfig

class App : Application() {
    init {
        instance = this
    }

    companion object {
        var instance: App? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        createComponent()
    }

    private fun createComponent() {
        component = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .androidModule(AndroidModule()).build()
    }

    private fun initializeLeakDetection(){
        when {
            BuildConfig.DEBUG -> {
                //LeakCanary.install(this)
            }
        }
    }
}