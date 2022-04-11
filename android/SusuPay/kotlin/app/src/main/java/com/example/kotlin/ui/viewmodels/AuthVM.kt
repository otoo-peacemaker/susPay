package com.example.kotlin.ui.viewmodels

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
