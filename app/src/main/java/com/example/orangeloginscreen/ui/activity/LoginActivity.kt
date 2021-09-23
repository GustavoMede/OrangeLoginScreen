package com.example.orangeloginscreen.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.orangeloginscreen.dao.UserDao
import com.example.orangeloginscreen.databinding.ActivityLoginFormBinding
import com.example.orangeloginscreen.model.User

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginFormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loginFormLoginButton.setOnClickListener {

            val usuario = User(binding.loginFormUsername.text.toString(), binding.loginFormPassword.text.toString())

            val dao = UserDao()

            dao.addUser(usuario)

            val intent = Intent(this, ListUsersActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()

        binding.loginFormUsername.setText("")
        binding.loginFormPassword.setText("")
    }
}