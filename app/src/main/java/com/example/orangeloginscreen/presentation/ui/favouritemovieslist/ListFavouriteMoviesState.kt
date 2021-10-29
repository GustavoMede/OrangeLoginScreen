package com.example.orangeloginscreen.presentation.ui.favouritemovieslist

import com.example.orangeloginscreen.domain.model.Movie

sealed class ListFavouriteMoviesState {

    object IDLE: ListFavouriteMoviesState()

    data class onListLoaded(val movies: List<Movie>): ListFavouriteMoviesState()

    data class onError(val message: String): ListFavouriteMoviesState()

    object onLoading: ListFavouriteMoviesState()
}