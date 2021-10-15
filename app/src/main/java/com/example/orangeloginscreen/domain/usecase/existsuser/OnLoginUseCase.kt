package com.example.orangeloginscreen.domain.usecase.existsuser

import com.example.orangeloginscreen.domain.model.User

interface OnLoginUseCase {

    suspend fun onLogin(username: String, password: String): User
}