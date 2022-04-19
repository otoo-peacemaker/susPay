package com.example.architecturaltemplate.di.component

import com.example.architecturaltemplate.ui.activity.MainActivity
import com.example.architecturaltemplate.di.module.ActivityModule
import com.example.architecturaltemplate.di.module.AndroidModule
import com.example.architecturaltemplate.di.module.ApplicationModule
import com.example.architecturaltemplate.network.RemoteDataSource
import com.example.architecturaltemplate.ui.fragment.LoginFragment

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        AndroidModule::class,
        ActivityModule::class]
)

interface AppComponent {
    fun inject(fragment: MainActivity)

   // fun inject(activity: LoginFragment)

    fun inject(fragment: LoginFragment)

    fun inject(fragment: RemoteDataSource)

}