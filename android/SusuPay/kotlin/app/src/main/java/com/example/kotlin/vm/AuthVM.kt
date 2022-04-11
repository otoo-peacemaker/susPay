package com.example.kotlin.vm
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.dao.AuthsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import javax.inject.Inject

/*
@HiltViewModel
class AuthVM @Inject constructor(var authRepository: AuthRepository) : ViewModel() {

    private val loading = MutableLiveData<Boolean>()
    private val errorMessage = MutableLiveData<String>()
    private val _responseLiveData: MutableLiveData<AuthsResponse> = MutableLiveData()
    val responseLiveData: LiveData<AuthsResponse> get() = _responseLiveData

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }


    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        _responseLiveData.value = null
    }
}*/
