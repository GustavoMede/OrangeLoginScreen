package com.example.orangeloginscreen.presentation.ui.login

import com.example.orangeloginscreen.domain.model.User

sealed class LoginState {

    object IDLE: LoginState()

    data class onSuccess(val user: User): LoginState()

    data class onError(val message: String): LoginState()

    object onLoading: LoginState()

    object onValidating: LoginState()

    object onValidated: LoginState()
}