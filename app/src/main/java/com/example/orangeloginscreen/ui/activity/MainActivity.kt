package com.example.orangeloginscreen.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.orangeloginscreen.R
import com.example.orangeloginscreen.dao.UsuarioDao
import com.example.orangeloginscreen.ui.RecyclerView.adapter.ListaUsuariosAdapter

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val dao = UsuarioDao()
        recyclerView.adapter = ListaUsuariosAdapter(
            dao.listaTodosUsuarios(),
            this
        )

    }
}