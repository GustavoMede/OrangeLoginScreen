package com.example.orangeloginscreen.ui.RecyclerView.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orangeloginscreen.R
import com.example.orangeloginscreen.model.Usuario

class ListaUsuariosAdapter(
    private val usuarios: List<Usuario>,
    private val context: Context
) : RecyclerView.Adapter<ListaUsuariosAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincula(usuario: Usuario) {
            val username = itemView.findViewById<TextView>(R.id.username)
            username.text = usuario.username
            val password = itemView.findViewById<TextView>(R.id.password)
            password.text = usuario.password
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.activity_lista_usuarios, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usuario = usuarios[position]
        holder.vincula(usuario)
    }

    override fun getItemCount(): Int = usuarios.size
}
