package com.example.orangeloginscreen.presentation.ui.createaccount

sealed class CreateAccountState {

    object IDLE: CreateAccountState()

    data class onSuccess(val message: String): CreateAccountState()

    data class onError(val message: String): CreateAccountState()

    object onLoading: CreateAccountState()
}