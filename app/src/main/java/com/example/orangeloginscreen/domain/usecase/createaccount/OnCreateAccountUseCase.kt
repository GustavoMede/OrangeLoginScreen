package com.example.orangeloginscreen.domain.usecase.createaccount

import com.example.orangeloginscreen.domain.model.User

interface OnCreateAccountUseCase {

    suspend fun onCreateAccount(username: String, password: String, passwordConfirmationRecieved: String)
}