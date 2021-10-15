package com.example.orangeloginscreen.data.source

import com.example.orangeloginscreen.domain.model.User
import kotlinx.coroutines.delay
import java.lang.Exception

class UserLocalDataSourceImpl: UserLocalDataSource {

    override fun add(username: String, password: String): User {
        val user = User(username = username, password = password)
        users.add(user)
        return user
    }

    override suspend fun list(): List<User> {
        delay(1000)
        return users.toList()
    }

    override suspend fun update(user: User) {
        delay(1000)
        find(user.id)?.let {
            val userFound = it

            userFound.username = user.username
            userFound.password = user.password
        }

    }

    override fun find(idRecieved: String): User? {
        for (i in users.indices) {
            if (users[i].id.equals(idRecieved)) {
                return users[i]
            }
        }
        return null
    }

    override suspend fun onLogin(username: String, password: String): User {
        delay(1000)
        return getUserByCredentials(username, password)
    }

    private fun getUserByCredentials(username: String, password: String): User {
        val user = users.find {
            username == it.username && password == it.password
        }
        return user ?: throw Exception()
    }

    override suspend fun onCreateAccount(username: String, password: String, passwordConfirmationRecieved: String): User {
        delay(1000)
        return validateCredentialsConfirmation(username, password, passwordConfirmationRecieved)
    }

    private fun validateCredentialsConfirmation(username: String, password: String, passwordConfirmationRecieved: String): User {
        if(password == passwordConfirmationRecieved){
            return add(username, password)
        }
        throw Exception()
    }

    companion object {
        private val users = mutableListOf<User>()
    }
}