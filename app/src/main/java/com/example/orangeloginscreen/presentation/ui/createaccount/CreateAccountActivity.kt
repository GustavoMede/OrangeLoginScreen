package com.example.orangeloginscreen.presentation.ui.createaccount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.orangeloginscreen.R
import com.example.orangeloginscreen.databinding.ActivityCreateAccountBinding
import com.example.orangeloginscreen.domain.model.User
import com.example.orangeloginscreen.presentation.ui.loadingdialog.LoadingDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAccountBinding

    //Usei o inject e funcionou da mesma forma, muda algo?
    private val createAccountViewModel: CreateAccountViewModel by viewModel()

    private val loadingDialog by lazy {
        LoadingDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setOnClickListener()
        configureToolbar()
        onObserverState()
    }

    private fun createUser() {
        saveUser()
    }

    private fun setOnClickListener() {
        binding.createAccountSaveAccountCreationButton.setOnClickListener {
            createUser()
        }

        binding.createAccountCancelCreateAccountButton.setOnClickListener {
            finish()
        }
    }

    private fun saveUser() {
        val passwordConfirmationRecieved = binding.createAccountConfirmPassword.text.toString()
        createAccountViewModel.createAccount(
                binding.createAccountUsername.text.toString(),
                binding.createAccountPassword.text.toString(),
                passwordConfirmationRecieved
        )
    }

    private fun configureToolbar() {
        setSupportActionBar(binding.createAccountToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun onObserverState() {
        createAccountViewModel.viewState.observe(this) {
            when (it) {
                is CreateAccountState.IDLE -> {
                }
                is CreateAccountState.onLoading -> onLoading()
                is CreateAccountState.onError -> showError(it.message)
                is CreateAccountState.onValidating -> onValidating()
                is CreateAccountState.onSuccess -> onSuccess(it.message)
            }
        }
    }

    private fun onValidating() {
        loadingDialog.startLoadingDialog(getString(R.string.progress_validating))
    }

    private fun showError(message: String) {
        loadingDialog.finalizeLoadingDialog()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun onLoading() {
        loadingDialog.startLoadingDialog(getString(R.string.progress_loading))
    }

    private fun onSuccess(message: String) {
        loadingDialog.finalizeLoadingDialog()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }
}