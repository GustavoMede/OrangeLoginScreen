package com.example.orangeloginscreen.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.orangeloginscreen.dao.UserDao
import com.example.orangeloginscreen.databinding.ActivityEditUserDataBinding
import com.example.orangeloginscreen.model.User

class EditUserDataActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityEditUserDataBinding.inflate(layoutInflater)
    }

    private val user by lazy { intent.getParcelableExtra<User>(User::javaClass.name) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setInfo()
        setOnClickListener()
    }

    private fun setInfo() {
        user?.let {
            binding.editUserUsername.setText(it.username)
            binding.editUserPassword.setText(it.password)
        }
    }

    private fun setOnClickListener() {
        binding.editUserSaveChangesButton.setOnClickListener {
            updateUser()
            finish()
        }

        binding.editUserCancelChangesButton.setOnClickListener {
            finish()
        }
    }

    private fun updateUser() {
        user?.let { user ->

            user.username = binding.editUserUsername.text.toString()
            user.password = binding.editUserPassword.text.toString()

            val dao = UserDao()
            dao.updateUser(user)
        }
    }
}
