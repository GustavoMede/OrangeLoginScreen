package com.example.orangeloginscreen.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.orangeloginscreen.dao.UserDao
import com.example.orangeloginscreen.databinding.ActivityMainBinding
import com.example.orangeloginscreen.model.User
import com.example.orangeloginscreen.ui.RecyclerView.adapter.ListaUsuariosAdapter
import com.example.orangeloginscreen.ui.RecyclerView.adapter.OnUserClicked

class ListUsersActivity(

) : AppCompatActivity(), OnUserClicked {

    private lateinit var binding: ActivityMainBinding
    private val dao = UserDao()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        listaUsuarios()
    }

    override fun onRestart() {
        super.onRestart()
        listaUsuarios()
    }

    override fun oneUserSelected(user: User, position: Int) {
        Toast.makeText(this, "item $user", Toast.LENGTH_SHORT).show()
        val editIntent = Intent(this, EditUserDataActivity::class.java)
        editIntent.putExtra("user", user)
        editIntent.putExtra("position", position)
        startActivity(editIntent)
    }

    fun listaUsuarios(){
        binding.recyclerView.adapter = ListaUsuariosAdapter(
            dao.listUsers(), this
        )
    }
}