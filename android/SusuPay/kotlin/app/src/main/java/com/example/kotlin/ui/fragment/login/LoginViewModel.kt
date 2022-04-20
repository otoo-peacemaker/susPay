package com.example.kotlin.ui.fragment.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.dao.LoginResponse
import com.example.kotlin.network.Resource
import com.example.kotlin.network.Status
import com.example.kotlin.repository.UserRepository
import com.example.kotlin.util.Constants
import com.example.kotlin.util.UserPreferences
import com.example.kotlin.util.UtilityMethods
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    /**
     * The internal MutableLiveData that stores the status of the most recent request
     * and The external immutable LiveData for the request status
     * */
    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

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
        Log.d(TAG,"Login view model initiated")
    }

    private fun login(body: HashMap<String, String>) = viewModelScope.launch {
        _status.value = Status.LOADING
        try {
            _loginResponse.value = userRepository.login(body)
            _status.value = Status.SUCCESS
        } catch (e: Exception) {
            _status.value = Status.ERROR
        }

    }
    fun loginOnClickListener(){
         val paramObject = HashMap<String, String>()
         paramObject["username"] = _username.value.toString()
         paramObject["password"] = _password.value.toString()
        login(paramObject)
        Log.i("Login","Login clicked")
    }

      fun savePreference(value: LoginResponse) {
        UtilityMethods.setTokenValue(value.authData.token)
        UserPreferences.setPreference(
            Constants.PreferenceConstants.USER_ID, value.authData.email
        )

        UserPreferences.setPreference(
            Constants.PreferenceConstants.USER_PASSWORD,
            _password.value.toString()
        )
    }

    companion object{
        const val TAG="LoginViewModel"
    }

    override fun onCleared() {
        super.onCleared()
       // loginResponse.value = null
    }
}