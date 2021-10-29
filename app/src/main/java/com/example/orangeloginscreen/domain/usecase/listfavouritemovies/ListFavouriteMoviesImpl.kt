package com.example.orangeloginscreen.domain.usecase.listfavouritemovies

import com.example.orangeloginscreen.data.MovieRepository
import com.example.orangeloginscreen.domain.model.Movie

class ListFavouriteMoviesImpl(private val repository: MovieRepository): ListFavouriteMovies {

    override suspend fun listFavouriteMovies(): List<Movie> {
        return repository.listFavouriteMovies()
    }
}