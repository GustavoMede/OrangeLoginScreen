package com.example.orangeloginscreen.domain.usecase.getusers

import com.example.orangeloginscreen.domain.model.User

interface GetUsersUseCase {

    suspend fun getUsers(): List<User>
}