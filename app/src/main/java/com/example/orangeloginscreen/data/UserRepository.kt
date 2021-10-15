package com.example.orangeloginscreen.data

import com.example.orangeloginscreen.domain.model.User
import com.example.orangeloginscreen.data.source.UserLocalDataSourceImpl

class UserRepository(
    private val dao: UserLocalDataSourceImpl
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