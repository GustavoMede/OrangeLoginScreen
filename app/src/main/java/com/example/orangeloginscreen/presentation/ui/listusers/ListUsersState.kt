package com.example.orangeloginscreen.presentation.ui.listusers

import com.example.orangeloginscreen.domain.model.User

sealed class ListUsersState {

    object IDLE: ListUsersState()

    data class onListLoaded(val users: List<User>): ListUsersState()

    data class onError(val message: String): ListUsersState()

    object onLoading: ListUsersState()
}