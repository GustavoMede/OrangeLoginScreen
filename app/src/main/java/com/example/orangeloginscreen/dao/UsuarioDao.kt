package com.example.orangeloginscreen.dao

import com.example.orangeloginscreen.model.Usuario

class UsuarioDao {

    fun adiciona(usuario: Usuario){
        usuarios.add(usuario)
    }

    fun listaTodosUsuarios() : List<Usuario>{
        return usuarios.toList()
    }

    companion object {
        private val usuarios = mutableListOf<Usuario>()
    }
}