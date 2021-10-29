package com.example.orangeloginscreen.koin.modules

import com.example.orangeloginscreen.data.MovieRepository
import com.example.orangeloginscreen.data.UserRepository
import com.example.orangeloginscreen.data.source.localdatasource.MovieLocalDataSource
import com.example.orangeloginscreen.data.source.localdatasource.MovieLocalDataSourceImpl
import com.example.orangeloginscreen.data.source.localdatasource.UserLocalDataSource
import com.example.orangeloginscreen.data.source.localdatasource.UserLocalDataSourceImpl
import com.example.orangeloginscreen.data.source.remotedatasource.MovieRemoteDataSource
import com.example.orangeloginscreen.data.source.remotedatasource.MovieRemoteDataSourceImpl
import com.example.orangeloginscreen.domain.usecase.createaccount.OnCreateAccountUseCase
import com.example.orangeloginscreen.domain.usecase.createaccount.OnCreateAccountUseCaseImpl
import com.example.orangeloginscreen.domain.usecase.existsuser.OnLoginUseCase
import com.example.orangeloginscreen.domain.usecase.existsuser.OnLoginUseCaseImpl
import com.example.orangeloginscreen.domain.usecase.getmovies.GetMoviesUseCase
import com.example.orangeloginscreen.domain.usecase.getmovies.GetMoviesUseCaseImpl
import com.example.orangeloginscreen.domain.usecase.getusers.GetUsersUseCase
import com.example.orangeloginscreen.domain.usecase.getusers.GetUsersUseCaseImpl
import com.example.orangeloginscreen.domain.usecase.ismoviefavourited.IsMovieFavouritedUseCase
import com.example.orangeloginscreen.domain.usecase.ismoviefavourited.IsMovieFavouritedUseCaseImpl
import com.example.orangeloginscreen.domain.usecase.listfavouritemovies.ListFavouriteMovies
import com.example.orangeloginscreen.domain.usecase.listfavouritemovies.ListFavouriteMoviesImpl
import com.example.orangeloginscreen.domain.usecase.updateuser.UpdateUserUseCase
import com.example.orangeloginscreen.domain.usecase.updateuser.UpdateUserUseCaseImpl
import com.example.orangeloginscreen.presentation.ui.createaccount.CreateAccountViewModel
import com.example.orangeloginscreen.presentation.ui.edituser.EditUserViewModel
import com.example.orangeloginscreen.presentation.ui.favouritemovieslist.ListFavouriteMoviesViewModel
import com.example.orangeloginscreen.presentation.ui.listmovies.ListMoviesViewModel
import com.example.orangeloginscreen.presentation.ui.listusers.ListUsersViewModel
import com.example.orangeloginscreen.presentation.ui.login.LoginViewModel
import com.example.orangeloginscreen.presentation.ui.moviedetails.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single {
        MovieRepository(get(), get())
    }
    single {
        UserRepository(get())
    }
    single<OnCreateAccountUseCase> {
        OnCreateAccountUseCaseImpl(get())
    }
    single<OnLoginUseCase> {
        OnLoginUseCaseImpl(get())
    }
    single<GetMoviesUseCase> {
        GetMoviesUseCaseImpl(get())
    }
    single<GetUsersUseCase> {
        GetUsersUseCaseImpl(get())
    }
    single<UpdateUserUseCase> {
        UpdateUserUseCaseImpl(get())
    }
    single<ListFavouriteMovies> {
        ListFavouriteMoviesImpl(get())
    }
    single<IsMovieFavouritedUseCase> {
        IsMovieFavouritedUseCaseImpl(get())
    }
    single<MovieLocalDataSource> {
        MovieLocalDataSourceImpl()
    }
    single<MovieRemoteDataSource> {
        MovieRemoteDataSourceImpl()
    }
    single<UserLocalDataSource> {
        UserLocalDataSourceImpl()
    }
    viewModel { CreateAccountViewModel(get()) }
    viewModel { EditUserViewModel(get()) }
    viewModel { ListMoviesViewModel(get()) }
    viewModel { ListUsersViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { ListFavouriteMoviesViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
}