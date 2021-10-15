package com.example.orangeloginscreen.presentation.ui.listusers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.orangeloginscreen.R
import com.example.orangeloginscreen.databinding.ActivityListUsersBinding
import com.example.orangeloginscreen.domain.model.User
import com.example.orangeloginscreen.RecyclerView.adapter.ListUsersAdapter
import com.example.orangeloginscreen.RecyclerView.adapter.UserListener
import com.example.orangeloginscreen.presentation.ui.edituser.EditUserDataActivity
import com.example.orangeloginscreen.presentation.ui.loadingdialog.LoadingDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListUsersActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListUsersBinding.inflate(layoutInflater)
    }

    private val listUsersViewModel: ListUsersViewModel by viewModel()

    private val loadingDialog by lazy {
        LoadingDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureToolbar()
        listUsers()
        onObserverState()
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

    private fun configureAdapter(users: List<User>) {
        binding.recyclerView.adapter = ListUsersAdapter(
            users, oneUserSelected
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

    private fun configureToolbar(){
        setSupportActionBar(binding.listUsersToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun listUsers(){
        listUsersViewModel.listUsers()
    }

    private fun onObserverState(){
        listUsersViewModel.viewState.observe(this) {
            when(it){
                is ListUsersState.IDLE -> {}
                is ListUsersState.onError -> showError(it.message)
                is ListUsersState.onListLoaded -> onListLoaded(it.users)
                is ListUsersState.onLoading -> onLoading()
            }
        }
    }

    private fun onLoading() {
        loadingDialog.startLoadingDialog(getString(R.string.progress_loading_users_list))
    }

    private fun onListLoaded(users: List<User>) {
        loadingDialog.finalizeLoadingDialog()
        configureAdapter(users)
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}