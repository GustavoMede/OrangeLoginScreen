package com.example.orangeloginscreen.koin.modules

import com.example.orangeloginscreen.data.MovieRepository
import com.example.orangeloginscreen.data.UserRepository
import com.example.orangeloginscreen.data.source.MovieLocalDataSourceImpl
import com.example.orangeloginscreen.data.source.UserLocalDataSourceImpl
import com.example.orangeloginscreen.domain.usecase.clearmovielist.ClearMovieListUseCaseImpl
import com.example.orangeloginscreen.domain.usecase.createaccount.OnCreateAccountUseCase
import com.example.orangeloginscreen.domain.usecase.createaccount.OnCreateAccountUseCaseImpl
import com.example.orangeloginscreen.domain.usecase.createmovies.CreateMoviesUseCaseImpl
import com.example.orangeloginscreen.domain.usecase.existsuser.OnLoginUseCaseImpl
import com.example.orangeloginscreen.domain.usecase.getmovies.GetMoviesUseCaseImpl
import com.example.orangeloginscreen.domain.usecase.getusers.GetUsersUseCaseImpl
import com.example.orangeloginscreen.domain.usecase.updateuser.UpdateUserUseCaseImpl
import com.example.orangeloginscreen.presentation.ui.createaccount.CreateAccountViewModel
import com.example.orangeloginscreen.presentation.ui.edituser.EditUserViewModel
import com.example.orangeloginscreen.presentation.ui.listmovies.ListMoviesViewModel
import com.example.orangeloginscreen.presentation.ui.listusers.ListUsersViewModel
import com.example.orangeloginscreen.presentation.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single<MovieRepository> {
        MovieRepository(get())
    }
    single<UserRepository> {
        UserRepository(get())
    }
    single<ClearMovieListUseCaseImpl> {
        ClearMovieListUseCaseImpl(get())
    }
    single<OnCreateAccountUseCase> {
        OnCreateAccountUseCaseImpl(get())
    }
    single<CreateMoviesUseCaseImpl> {
        CreateMoviesUseCaseImpl(get())
    }
    single<OnLoginUseCaseImpl> {
        OnLoginUseCaseImpl(get())
    }
    single<GetMoviesUseCaseImpl> {
        GetMoviesUseCaseImpl(get())
    }
    single<GetUsersUseCaseImpl> {
        GetUsersUseCaseImpl(get())
    }
    single<UpdateUserUseCaseImpl> {
        UpdateUserUseCaseImpl(get())
    }
    single<MovieLocalDataSourceImpl> {
        MovieLocalDataSourceImpl()
    }
    single<UserLocalDataSourceImpl> {
        UserLocalDataSourceImpl()
    }
    viewModel<CreateAccountViewModel> { CreateAccountViewModel(get()) }
    viewModel<EditUserViewModel> { EditUserViewModel(get()) }
    viewModel<ListMoviesViewModel> { ListMoviesViewModel(get(), get(), get()) }
    viewModel<ListUsersViewModel> { ListUsersViewModel(get()) }
    viewModel<LoginViewModel> { LoginViewModel(get()) }
}