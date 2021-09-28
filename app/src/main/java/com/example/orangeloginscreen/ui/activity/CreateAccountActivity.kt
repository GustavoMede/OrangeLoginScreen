package com.example.orangeloginscreen.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.orangeloginscreen.dao.UserDao
import com.example.orangeloginscreen.databinding.ActivityCreateAccountBinding
import com.example.orangeloginscreen.model.User

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setOnClickListener()

    }

    private fun createUser() {
        if(isValidPassword()) {
            saveUser()
            finish()
        }else {
            showPasswordError()
        }
    }

    private fun isValidPassword() : Boolean {
        val password = binding.createAccountPassword.text.toString()
        val passwordConfirmRecieved = binding.createAccountConfirmPassword.text.toString()

        return password == passwordConfirmRecieved
    }

    private fun setOnClickListener() {
        binding.createAccountSaveAccountCreationButton.setOnClickListener {
            createUser()
        }

        binding.createAccountCancelCreateAccountButton.setOnClickListener {
            finish()
        }
    }

    private fun saveUser() {
        val dao = UserDao()

        dao.addUser(
            User(
                username = binding.createAccountUsername.text.toString(),
                password = binding.createAccountPassword.text.toString()
            )
        )
    }

    private fun showPasswordError() {
        Toast.makeText(this, "The passwords doesn't match!", Toast.LENGTH_SHORT).show()
    }
}