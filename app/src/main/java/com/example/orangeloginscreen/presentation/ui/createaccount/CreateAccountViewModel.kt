package com.example.orangeloginscreen.presentation.ui.createaccount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orangeloginscreen.domain.model.User
import com.example.orangeloginscreen.domain.usecase.createaccount.OnCreateAccountUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CreateAccountViewModel(
    private val onCreateAccountUseCase: OnCreateAccountUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<CreateAccountState>(CreateAccountState.IDLE)

    val viewState : LiveData<CreateAccountState> get() = _viewState

    fun createAccount(username: String, password: String, passwordConfirmationRecieved: String){
        viewModelScope.launch {
            try {
                _viewState.value = CreateAccountState.onLoading
                delay(1000)
                onCreateAccountUseCase.onCreateAccount(username, password, passwordConfirmationRecieved)
                _viewState.value = CreateAccountState.onSuccess("Account successfully created!")
            }catch (e: Exception){
                _viewState.value = CreateAccountState.onError(e.localizedMessage.orEmpty())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}