package com.example.orangeloginscreen.presentation.ui.listusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orangeloginscreen.domain.usecase.getusers.GetUsersUseCase
import kotlinx.coroutines.launch

class ListUsersViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<ListUsersState>(ListUsersState.IDLE)

    val viewState: LiveData<ListUsersState> get() = _viewState

    fun listUsers() {
        viewModelScope.launch {
            try {
                _viewState.value = ListUsersState.onLoading
                val users = getUsersUseCase.getUsers()
                _viewState.value = ListUsersState.onListLoaded(users)
            } catch (e: Exception) {
                _viewState.value = ListUsersState.onError(e.localizedMessage.orEmpty())
            }
        }
    }
}