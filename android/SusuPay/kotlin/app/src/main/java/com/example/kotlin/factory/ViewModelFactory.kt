package com.example.kotlin.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.repository.AuthRepository
import com.example.kotlin.repository.BaseRepository
import com.example.kotlin.repository.UserRepository
import com.example.kotlin.ui.fragment.LandingViewModel
import com.example.kotlin.ui.fragment.home.HomeViewModel
import com.example.kotlin.ui.fragment.login.LoginViewModel
import com.example.kotlin.ui.fragment.register.RegistrationViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: BaseRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LandingViewModel::class.java) -> LandingViewModel(
                repository as UserRepository
            ) as T

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(
                repository as AuthRepository
            ) as T

            modelClass.isAssignableFrom(RegistrationViewModel::class.java) -> RegistrationViewModel(
                repository as UserRepository
            ) as T


            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
                repository as UserRepository
            ) as T

            else -> {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }


    }
}