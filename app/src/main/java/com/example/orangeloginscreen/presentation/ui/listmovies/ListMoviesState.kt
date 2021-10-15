package com.example.orangeloginscreen.presentation.ui.listmovies

import com.example.orangeloginscreen.domain.model.Movie

sealed class ListMoviesState {

    object IDLE: ListMoviesState()

    data class onListLoaded(val movies: List<Movie>): ListMoviesState()

    data class onError(val message: String): ListMoviesState()

    object onLoading: ListMoviesState()

    object onListCreated: ListMoviesState()
}