package com.example.orangeloginscreen.domain.usecase.createmovies

import com.example.orangeloginscreen.data.MovieRepository

class CreateMoviesUseCaseImpl(
    val repository: MovieRepository
): CreateMoviesUseCase {

    override suspend fun createMovies(){
        repository.generateListOfMovies()
    }
}