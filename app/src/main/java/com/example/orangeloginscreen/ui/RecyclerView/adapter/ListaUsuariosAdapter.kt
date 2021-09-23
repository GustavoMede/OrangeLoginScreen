package com.example.orangeloginscreen.ui.RecyclerView.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orangeloginscreen.databinding.ActivityListUsersBinding
import com.example.orangeloginscreen.model.User

class ListaUsuariosAdapter(
    private val users: List<User>,
    private val listener: OnUserClicked
) : RecyclerView.Adapter<ViewHolder>() {

    lateinit var onUserClicked: OnUserClicked

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ActivityListUsersBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usuario = users[position]
        holder.vincula(usuario, listener, users)
    }

    override fun getItemCount(): Int = users.size
}

class ViewHolder(val view: ActivityListUsersBinding) :
    RecyclerView.ViewHolder(view.root) {

    fun vincula(user: User, listener: OnUserClicked, users: List<User>) {
        view.loginFormUsername.setText(user.username)
        view.loginFormPassword.setText(user.password)

        view.root.setOnClickListener {
            val userSelected = users[adapterPosition]
            listener.oneUserSelected(userSelected, adapterPosition)
        }
    }
}
