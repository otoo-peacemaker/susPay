package com.example.kotlin.di.component

import com.example.kotlin.di.module.NetworkModule
import com.example.kotlin.repository.remote.RemoteDataSource
import com.example.kotlin.ui.activity.MainActivity
import com.example.kotlin.ui.fragment.login.LoginFragment
import com.example.kotlin.ui.fragment.register.RegistrationFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class]
)

interface AppComponent {
    fun inject(fragment: MainActivity)

    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: RemoteDataSource)

}