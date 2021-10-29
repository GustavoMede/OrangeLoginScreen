package com.example.orangeloginscreen.domain.usecase.ismoviefavourited

import com.example.orangeloginscreen.data.MovieRepository
import com.example.orangeloginscreen.domain.model.Movie

class IsMovieFavouritedUseCaseImpl(
    private val repository: MovieRepository
): IsMovieFavouritedUseCase {

    override fun toggleFavouriteMovie(movie: Movie): Movie {
        return repository.toggleFavouriteMovie(movie)
    }
}