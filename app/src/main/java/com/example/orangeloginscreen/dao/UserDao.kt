package com.example.orangeloginscreen.dao

import com.example.orangeloginscreen.model.User

class UserDao {

    fun addUser(user: User){
        users.add(user)
    }

    fun listUsers() : List<User>{
        return users.toList()
    }

    fun updateUser(position: Int, user: User) {
        val userFound = users[position]
        userFound.username = user.username
        userFound.password = user.password
    }

    companion object {
        private val users = mutableListOf<User>()
    }
}