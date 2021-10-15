package com.example.orangeloginscreen.domain.usecase.updateuser

import com.example.orangeloginscreen.data.UserRepository
import com.example.orangeloginscreen.domain.model.User

class UpdateUserUseCaseImpl(
    val repository: UserRepository
): UpdateUserUseCase {

    override suspend fun updateUser(user: User){
        repository.updateUser(user)
    }
}