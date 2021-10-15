package com.example.orangeloginscreen.domain.usecase.getusers

import com.example.orangeloginscreen.data.UserRepository
import com.example.orangeloginscreen.domain.model.User

class GetUsersUseCaseImpl(
    val repository: UserRepository
): GetUsersUseCase {

    override suspend fun getUsers(): List<User> {
        return repository.listUsers()
    }
}