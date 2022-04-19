package com.example.kotlin.di.app

import android.app.Activity
import android.app.Application
import android.content.Context
import com.example.kotlin.BuildConfig
import com.example.kotlin.di.component.AppComponent
import com.example.kotlin.di.module.ActivityModule
import com.example.kotlin.di.module.AndroidModule
import com.example.kotlin.di.module.ApplicationModule

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
        component = DaggerAppComponent.build()
            .applicationModule(ApplicationModule(this))
            .androidModule(AndroidModule())
            .activityModule(ActivityModule(activity = Activity()))
            .build()
    }

    private fun initializeLeakDetection(){
        when {
            BuildConfig.DEBUG -> {
                //LeakCanary.install(this)
            }
        }
    }
}