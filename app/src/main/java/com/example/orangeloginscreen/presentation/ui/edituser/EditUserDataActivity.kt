package com.example.orangeloginscreen.presentation.ui.edituser

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.orangeloginscreen.R
import com.example.orangeloginscreen.databinding.ActivityEditUserDataBinding
import com.example.orangeloginscreen.domain.model.User
import com.example.orangeloginscreen.presentation.ui.loadingdialog.LoadingDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditUserDataActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityEditUserDataBinding.inflate(layoutInflater)
    }

    private val user by lazy { intent.getParcelableExtra<User>(User::javaClass.name) }

    private val editUserViewModel: EditUserViewModel by viewModel()

    private val loadingDialog by lazy {
        LoadingDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setInfo()
        setOnClickListener()
        configureToolbar()
        onObserverState()
    }

    private fun setInfo() {
        user?.let {
            binding.editUserUsername.setText(it.username)
            binding.editUserPassword.setText(it.password)
        }
    }

    private fun setOnClickListener() {
        binding.editUserSaveChangesButton.setOnClickListener {
            updateUser()
        }

        binding.editUserCancelChangesButton.setOnClickListener {
            finish()
        }
    }

    private fun updateUser() {
        user?.let { user ->

            user.password = binding.editUserPassword.text.toString()
            user.username = binding.editUserUsername.text.toString()
            editUserViewModel.updateUser(user)
        }

        //CoroutineScope(Dispatchers.IO).launch {
        //user?.let { user ->

        // user.username = binding.editUserUsername.text.toString()
        // user.password = binding.editUserPassword.text.toString()

        //  val updateUserUseCase = UpdateUserUseCase()
        //  updateUserUseCase.updateUser(user)
        // }
    }

    private fun configureToolbar() {
        setSupportActionBar(binding.editUserToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun onObserverState(){

        editUserViewModel.viewState.observe(this) {
            when(it){
                is EditUserState.IDLE -> {}
                is EditUserState.onError -> showError(it.message)
                is EditUserState.onLoading -> onLoading()
                is EditUserState.onSuccess -> onSuccess()
            }
        }
    }

    private fun onSuccess() {
        loadingDialog.finalizeLoadingDialog()
        finish()
    }

    private fun onLoading() {
        loadingDialog.startLoadingDialog(getString(R.string.progress_saving_changes))
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
