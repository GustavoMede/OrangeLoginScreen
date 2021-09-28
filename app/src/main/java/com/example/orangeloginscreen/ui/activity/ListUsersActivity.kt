package com.example.orangeloginscreen.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.orangeloginscreen.dao.UserDao
import com.example.orangeloginscreen.databinding.ActivityListUsersBinding
import com.example.orangeloginscreen.model.Movie
import com.example.orangeloginscreen.model.User
import com.example.orangeloginscreen.ui.RecyclerView.adapter.ListMoviesAdapter
import com.example.orangeloginscreen.ui.RecyclerView.adapter.ListUsersAdapter
import com.example.orangeloginscreen.ui.RecyclerView.adapter.UserListener

class ListUsersActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListUsersBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        UserDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureAdapter()
    }

    override fun onRestart() {
        super.onRestart()
        notifyDataSetChanged()
    }

    private val oneUserSelected = object : UserListener {
        override fun invoke(user: User) {
            sendEditIntent(user)
        }
    }

    private fun configureAdapter() {
        binding.recyclerView.adapter = ListUsersAdapter(
            dao.listUsers(), oneUserSelected
        )
    }

    private fun notifyDataSetChanged() {
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun sendEditIntent(user: User) {
        val editIntent = Intent(this@ListUsersActivity, EditUserDataActivity::class.java)
        editIntent.putExtra(User::javaClass.name, user)
        startActivity(editIntent)
    }
}