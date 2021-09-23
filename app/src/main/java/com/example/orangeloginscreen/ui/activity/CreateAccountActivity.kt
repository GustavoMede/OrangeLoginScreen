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

        binding.createAccountSaveAccountCreationButton.setOnClickListener {
            if(binding.createAccountPassword.text.toString() != binding.createAccountConfirmPassword.text.toString()){
                Toast.makeText(this, "The passwords doesn't match!", Toast.LENGTH_SHORT).show()
            }else {
                val dao = UserDao()

                dao.addUser(
                    User(
                        binding.createAccountUsername.text.toString(),
                        binding.createAccountPassword.text.toString()
                    )
                )

                Toast.makeText(this, "Account successfully created!", Toast.LENGTH_SHORT).show()

                finish()
            }
        }

        binding.createAccountCancelCreateAccountButton.setOnClickListener {
            finish()
        }

    }
}