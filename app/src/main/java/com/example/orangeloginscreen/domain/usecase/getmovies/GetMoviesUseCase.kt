package com.example.orangeloginscreen.domain.usecase.getmovies

import com.example.orangeloginscreen.domain.model.Movie

interface GetMoviesUseCase {

    suspend fun getMovies() : List<Movie>
}