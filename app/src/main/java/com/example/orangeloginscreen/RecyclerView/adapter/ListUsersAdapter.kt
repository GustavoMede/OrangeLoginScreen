package com.example.orangeloginscreen.RecyclerView.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orangeloginscreen.databinding.ActivityUserItemBinding
import com.example.orangeloginscreen.domain.model.User

typealias UserListener = (User) -> Unit

class ListUsersAdapter(
    private val users: List<User>,
    private val listener: UserListener
) : RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ActivityUserItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user, listener, users)
    }

    override fun getItemCount() = users.size

}

class UserViewHolder(val view: ActivityUserItemBinding) :
    RecyclerView.ViewHolder(view.root) {

    fun bind(user: User, listener: UserListener, users: List<User>) {
        view.userItemUsername.text = user.username
        view.userItemPassword.text = user.password

        view.root.setOnClickListener {
            val userSelected = users[adapterPosition]
            listener.invoke(userSelected)
        }
    }
}