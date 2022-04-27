package com.example.kotlin.ui.fragment.register

import androidx.lifecycle.ViewModel
import com.example.kotlin.repository.AuthRepository
import com.example.kotlin.repository.UserRepository
import com.example.kotlin.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(val repository: AuthRepository) : BaseViewModel(repository) {
    // TODO: Implement the ViewModel

    companion object{
        val instant = RegistrationViewModel
    }
}