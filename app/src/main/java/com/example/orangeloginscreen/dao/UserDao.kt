package com.example.orangeloginscreen.dao

import com.example.orangeloginscreen.model.User

class UserDao {

    fun addUser(user: User){
        users.add(user)
    }

    fun listUsers() : List<User>{
        return users.toList()
    }

    //Refatorar utilizando id do usuário
    fun updateUser(user: User) {
        findById(user.id)?.let {
            val userFound = it

            userFound.username = user.username
            userFound.password = user.password
        }

    }

    fun existsUser(username: String, password: String): Boolean{
        for(i in users.indices){
            if(users[i].username == username && users[i].password == password){
                return true
            }
        }
        return false
    }

    fun findById(idRecieved : String): User? {
        for(i in users.indices){
            if(users[i].id.equals(idRecieved)){
                return users[i]
            }
        }
        return null
    }

    companion object {
        private val users = mutableListOf<User>()
    }
}