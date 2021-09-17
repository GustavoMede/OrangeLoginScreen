package com.example.orangeloginscreen.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.orangeloginscreen.R
import com.example.orangeloginscreen.dao.UsuarioDao
import com.example.orangeloginscreen.model.Usuario

class LoginActivity : AppCompatActivity(R.layout.activity_login_form) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val signInButton = findViewById<Button>(R.id.botao_login)

        signInButton.setOnClickListener {
            val usernameField = findViewById<EditText>(R.id.username)
            val username = usernameField.text.toString()
            val passwordField = findViewById<EditText>(R.id.password)
            val password = passwordField.text.toString()

            val usuario = Usuario(username, password)

            val dao = UsuarioDao()

            dao.adiciona(usuario)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}