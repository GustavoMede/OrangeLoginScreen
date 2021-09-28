package com.example.orangeloginscreen.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.orangeloginscreen.dao.UserDao
import com.example.orangeloginscreen.databinding.ActivityLoginFormBinding

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginFormBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        UserDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = "Login"
        setOnClickListener()
    }

    override fun onRestart() {
        super.onRestart()
        setData()
    }

    private fun login() {
        val usernameRecieved = binding.loginFormUsername.text.toString()
        val passwordRecieved = binding.loginFormPassword.text.toString()
        if (dao.existsUser(usernameRecieved, passwordRecieved)) {
            setLoginIntent()
        }else {
            showLoginError()
        }
    }

    private fun setOnClickListener() {
        binding.loginFormLoginButton.setOnClickListener {
            login()
        }

        binding.loginFormCreateNewAccountButton.setOnClickListener {
            setCreateAccountIntent()
        }

        binding.loginFormListusersButton.setOnClickListener {
            setListUsersIntent()
        }
    }

    private fun setData() {
        binding.loginFormUsername.setText("")
        binding.loginFormPassword.setText("")
    }

    private fun setLoginIntent() {
        val loginIntent = Intent(this@LoginActivity, ListMoviesActivity::class.java)
        startActivity(loginIntent)
    }

    private fun setCreateAccountIntent() {
        val createAccountIntent = Intent(this@LoginActivity, CreateAccountActivity::class.java)
        startActivity(createAccountIntent)
    }

    private fun setListUsersIntent() {
        val listUsersIntent = Intent(this@LoginActivity, ListUsersActivity::class.java)
        startActivity(listUsersIntent)
    }

    private fun showLoginError() {
        Toast.makeText(this, "non-existent user", Toast.LENGTH_SHORT).show()
    }
}
