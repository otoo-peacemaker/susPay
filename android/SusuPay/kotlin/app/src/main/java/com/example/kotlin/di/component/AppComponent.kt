package com.example.kotlin.di.component

import com.example.kotlin.di.Scope
import com.example.kotlin.di.module.ActivityModule
import com.example.kotlin.di.module.AndroidModule
import com.example.kotlin.di.module.ApplicationModule
import com.example.kotlin.network.HttpRequest
import com.example.kotlin.repository.UserRepository
import com.example.kotlin.repository.remote.RemoteDataSource
import com.example.kotlin.ui.activity.MainActivity
import com.example.kotlin.ui.fragment.login.LoginFragment
import com.example.kotlin.ui.fragment.register.RegistrationFragment
import dagger.Component

@Scope
@Component(
    modules = [
        ApplicationModule::class,
        AndroidModule::class,
        ActivityModule::class]
)
interface AppComponent {
    fun inject(fragment: MainActivity)

 //   fun userRepository(): UserRepository

    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: RemoteDataSource)

}