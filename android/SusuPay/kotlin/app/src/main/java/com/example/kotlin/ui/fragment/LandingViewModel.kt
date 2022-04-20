package com.example.kotlin.ui.fragment

import androidx.lifecycle.ViewModel
import com.example.kotlin.repository.UserRepository
import javax.inject.Inject

class LandingViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel()