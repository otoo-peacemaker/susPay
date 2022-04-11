package com.example.architecturaltemplate.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturaltemplate.dao.AuthData
import com.example.architecturaltemplate.dao.LoginResponse
import com.example.architecturaltemplate.network.Resource
import com.example.architecturaltemplate.network.Status
import com.example.architecturaltemplate.repository.LoginRepository
import com.example.architecturaltemplate.util.Constants
import com.example.architecturaltemplate.util.PreferenceConfiguration
import com.triad.mvvmlearning.utility.UtilityMethods
import kotlinx.coroutines.launch

class LoginViewModel(private var repository: LoginRepository) : ViewModel() {
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
            _loginResponse.value = repository.login(body)
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
        PreferenceConfiguration.setPreference(
            Constants.PreferenceConstants.USER_ID, value.authData.email
        )

        PreferenceConfiguration.setPreference(
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