package com.example.orangeloginscreen.presentation.ui.edituser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orangeloginscreen.domain.model.User
import com.example.orangeloginscreen.domain.usecase.updateuser.UpdateUserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EditUserViewModel(
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel(){

    private val _viewState = MutableLiveData<EditUserState>(EditUserState.IDLE)

    val viewState : LiveData<EditUserState> get() = _viewState

    fun updateUser(user: User){

        viewModelScope.launch {
            try {
                _viewState.value = EditUserState.onLoading
                delay(1000)
                updateUserUseCase.updateUser(user)
                _viewState.value = EditUserState.onSuccess
            }catch (e: Exception){
                _viewState.value = EditUserState.onError(e.localizedMessage.orEmpty())
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
    }
}