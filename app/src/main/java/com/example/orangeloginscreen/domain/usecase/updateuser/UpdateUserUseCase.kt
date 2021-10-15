package com.example.orangeloginscreen.domain.usecase.updateuser

import com.example.orangeloginscreen.domain.model.User

interface UpdateUserUseCase {

    suspend fun updateUser(user: User)
}