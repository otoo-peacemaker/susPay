package com.example.kotlin.ui.fragment.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlin.model.LoginResponse
import com.example.kotlin.network.Resource
import com.example.kotlin.repository.AuthRepository
import com.example.kotlin.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel(repository) {


    private val _username = MutableLiveData<String>()
    val username: LiveData<String>
        get() = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password


    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    init {
        Log.d(TAG, "Login view model initiated")
    }

    private fun login(body: HashMap<String, String>) = viewModelScope.launch {

        try {
            _loginResponse.value = repository.login(body)

        } catch (e: Exception) {

        }
    }

    fun loginOnClickListener() {
        val paramObject = HashMap<String, String>()
        paramObject["username"] = _username.value.toString()
        paramObject["password"] = _password.value.toString()
        login(paramObject)
        Log.i("Login", "Login clicked")
    }

    companion object {
        const val TAG = "LoginViewModel"
    }

}