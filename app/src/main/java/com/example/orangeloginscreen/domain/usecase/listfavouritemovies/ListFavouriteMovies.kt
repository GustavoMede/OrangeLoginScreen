package com.example.orangeloginscreen.domain.usecase.listfavouritemovies

import com.example.orangeloginscreen.domain.model.Movie

interface ListFavouriteMovies {

    suspend fun listFavouriteMovies(): List<Movie>
}