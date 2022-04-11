package com.example.module1.di.app

import android.app.Application
import android.content.Context
import android.support.compat.BuildConfig
import com.example.architecturaltemplate.di.module.ApplicationModule

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
        if(BuildConfig.DEBUG){
            //LeakCanary.install(this)
        }
    }
}