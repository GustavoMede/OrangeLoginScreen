package com.example.orangeloginscreen.domain.usecase.existsuser

import com.example.orangeloginscreen.data.UserRepository
import com.example.orangeloginscreen.domain.model.User

class OnLoginUseCaseImpl(
    private val repository: UserRepository
): OnLoginUseCase {

    override suspend fun onLogin(username: String, password: String): User {
        return repository.onLogin(username, password)
    }
}