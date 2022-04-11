package com.example.module1.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.architecturaltemplate.repository.LoginRepository
import com.example.architecturaltemplate.viewmodel.LoginViewModel
import com.triad.mvvmlearning.repository.BaseRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: BaseRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when {

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(
                repository as LoginRepository
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