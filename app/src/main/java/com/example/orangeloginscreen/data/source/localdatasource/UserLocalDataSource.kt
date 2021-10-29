package com.example.orangeloginscreen.data.source.localdatasource

import com.example.orangeloginscreen.domain.model.User

interface UserLocalDataSource {

    fun add(username: String, password: String): User

    suspend fun list(): List<User>

    suspend fun update(user: User)

    fun find(idRecieved: String): User?

    suspend fun onLogin(username: String, password: String): User

    suspend fun onCreateAccount(username: String, password: String, passwordConfirmationRecieved: String): User
}