package com.example.orangeloginscreen.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.orangeloginscreen.dao.UserDao
import com.example.orangeloginscreen.databinding.ActivityEditUserDataBinding
import com.example.orangeloginscreen.databinding.ActivityMainBinding
import com.example.orangeloginscreen.model.User

class EditUserDataActivity : AppCompatActivity() {

    private lateinit var editBinding : ActivityEditUserDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editBinding = ActivityEditUserDataBinding.inflate(layoutInflater)
        val view = editBinding.root
        setContentView(view)

        val recievedUser = intent.getParcelableExtra<User>("user")
        val positionModifiedUser = intent.getIntExtra("position", 0)

        if(recievedUser != null) {
            editBinding.editUserUsername.setText(recievedUser.username)
            editBinding.editUserPassword.setText(recievedUser.password)

            editBinding.editUserSaveChangesButton.setOnClickListener {
                recievedUser.username = editBinding.editUserUsername.text.toString()
                recievedUser.password = editBinding.editUserPassword.text.toString()

                val dao = UserDao()
                dao.updateUser(positionModifiedUser, recievedUser)
                finish()
            }

            editBinding.editUserCancelChangesButton.setOnClickListener {
                finish()
            }
        }

        Toast.makeText(this, "Chegou na activity de editar o camarada", Toast.LENGTH_SHORT).show()
    }
}