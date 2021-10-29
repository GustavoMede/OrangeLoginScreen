package com.example.orangeloginscreen.data

import com.example.orangeloginscreen.data.source.localdatasource.UserLocalDataSource
import com.example.orangeloginscreen.domain.model.User

class UserRepository(
    private val dao: UserLocalDataSource
) {

    suspend fun onCreateAccount(
        username: String,
        password: String,
        passwordConfirmationRecieved: String,
    ){
        dao.onCreateAccount(username, password, passwordConfirmationRecieved)
    }

    suspend fun listUsers() : List<User>{
        return dao.list()
    }

    suspend fun updateUser(user: User) {
        dao.update(user)
    }

    suspend fun onLogin(username: String, password: String): User {
        return dao.onLogin(username, password)
    }
}