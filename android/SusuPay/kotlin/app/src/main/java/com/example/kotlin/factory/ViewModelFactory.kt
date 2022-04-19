package com.example.kotlin.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.repository.BaseRepository
import com.example.kotlin.repository.UserRepository
import com.example.kotlin.ui.fragment.login.LoginViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: BaseRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when {

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(
                repository as UserRepository
            ) as T

            /*modelClass.isAssignableFrom(NotificationsViewModel::class.java) -> NotificationsViewModel(
                repository as NotificationRepository
            ) as T

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
                repository as HomeRepository
            ) as T

            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> DashboardViewModel(
                repository as DashboardRepository
            ) as T

            modelClass.isAssignableFrom(NotificationsDetailsViewModel::class.java) -> NotificationsDetailsViewModel(
                repository as NotificationRepository
            ) as T
            modelClass.isAssignableFrom(SavedFeedbackViewModel::class.java) -> SavedFeedbackViewModel(
                repository as SavedFeedRepository
            ) as T*/

            else -> throw IllegalArgumentException("ViewModel Not Found")
        }

    }
}