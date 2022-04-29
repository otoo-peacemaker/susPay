package com.example.kotlin.ui.base

import androidx.lifecycle.ViewModel
import com.example.kotlin.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {
    suspend fun logout() = withContext(Dispatchers.IO) { repository.logout() }

}