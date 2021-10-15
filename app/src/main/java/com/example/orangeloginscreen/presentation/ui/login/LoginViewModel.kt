package com.example.orangeloginscreen.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orangeloginscreen.domain.usecase.existsuser.OnLoginUseCase
import com.example.orangeloginscreen.domain.usecase.existsuser.OnLoginUseCaseImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(
    private val onLoginUseCase: OnLoginUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<LoginState>(LoginState.IDLE)

    val viewState: LiveData<LoginState> get() = _viewState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                _viewState.value = LoginState.onLoading
                delay(1000)
                val user = onLoginUseCase.onLogin(username, password)
                _viewState.value = LoginState.onSuccess(user)
            } catch (e: Exception) {
                _viewState.value = LoginState.onError(e.localizedMessage.orEmpty())
            }
        }
    }
}