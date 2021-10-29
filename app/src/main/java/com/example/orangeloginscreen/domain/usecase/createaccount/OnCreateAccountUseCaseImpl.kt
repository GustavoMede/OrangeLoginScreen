package com.example.orangeloginscreen.domain.usecase.createaccount

import com.example.orangeloginscreen.data.UserRepository

class OnCreateAccountUseCaseImpl(
    private val repository: UserRepository
): OnCreateAccountUseCase {

    override suspend fun onCreateAccount(username: String, password: String, passwordConfirmationRecieved: String){
        repository.onCreateAccount(username, password, passwordConfirmationRecieved)
    }
}