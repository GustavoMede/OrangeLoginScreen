package com.example.orangeloginscreen.presentation.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.orangeloginscreen.R
import com.example.orangeloginscreen.databinding.ActivityLoginFormBinding
import com.example.orangeloginscreen.domain.model.User
import com.example.orangeloginscreen.presentation.ui.createaccount.CreateAccountActivity
import com.example.orangeloginscreen.presentation.ui.listmovies.ListMoviesActivity
import com.example.orangeloginscreen.presentation.ui.listusers.ListUsersActivity
import com.example.orangeloginscreen.presentation.ui.loadingdialog.LoadingDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginFormBinding.inflate(layoutInflater)
    }

    private val loginViewModel: LoginViewModel by viewModel()

    private val loadingDialog by lazy {
        LoadingDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = "Login"
        setOnClickListener()
        onObserverState()
    }

    override fun onRestart() {
        super.onRestart()
        setData()
    }

    private fun login() {
        loginViewModel.login(binding.loginFormUsername.text.toString(),
        binding.loginFormPassword.text.toString())
    }

    private fun setOnClickListener() {
        binding.loginFormLoginButton.setOnClickListener {
            login()
        }

        binding.loginFormCreateNewAccountButton.setOnClickListener {
            setCreateAccountIntent()
        }

        binding.loginFormListusersButton.setOnClickListener {
            setListUsersIntent()
        }
    }

    private fun setData() {
        binding.loginFormUsername.setText("")
        binding.loginFormPassword.setText("")
    }

    private fun setLoginIntent(signedInUser: User) {
        val loginIntent = Intent(this@LoginActivity, ListMoviesActivity::class.java)
        loginIntent.putExtra(User::javaClass.name, signedInUser)
        startActivity(loginIntent)
    }

    private fun setCreateAccountIntent() {
        val createAccountIntent = Intent(this@LoginActivity, CreateAccountActivity::class.java)
        startActivity(createAccountIntent)
    }

    private fun setListUsersIntent() {
        val listUsersIntent = Intent(this@LoginActivity, ListUsersActivity::class.java)
        startActivity(listUsersIntent)
    }

    private fun showError(message: String) {
        loadingDialog.finalizeLoadingDialog()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun onObserverState() {
        loginViewModel.viewState.observe(this) {
            when(it){
                is LoginState.IDLE -> {}
                is LoginState.onError -> showError(it.message)
                is LoginState.onLoading -> onLoading()
                is LoginState.onSuccess -> onSuccess(it.user)
            }
        }
    }

    private fun onSuccess(user: User) {
        loadingDialog.finalizeLoadingDialog()
        setLoginIntent(user)
    }

    private fun onLoading() {
        loadingDialog.startLoadingDialog(getString(R.string.progress_loading))
    }
}
