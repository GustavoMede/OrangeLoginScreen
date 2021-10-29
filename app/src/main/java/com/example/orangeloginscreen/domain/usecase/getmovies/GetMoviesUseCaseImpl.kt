package com.example.orangeloginscreen.domain.usecase.getmovies

import com.example.orangeloginscreen.data.MovieRepository
import com.example.orangeloginscreen.domain.model.Movie

class GetMoviesUseCaseImpl(
    private val repository: MovieRepository
) : GetMoviesUseCase {

    override suspend fun getMovies(): List<Movie> {
        return repository.getMovies()
    }
}