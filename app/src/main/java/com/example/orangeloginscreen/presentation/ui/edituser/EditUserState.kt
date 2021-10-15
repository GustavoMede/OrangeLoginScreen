package com.example.orangeloginscreen.presentation.ui.edituser

sealed class EditUserState {
    object IDLE: EditUserState()

    object onSuccess: EditUserState()

    data class onError(val message: String): EditUserState()

    object onLoading: EditUserState()
}