package com.example.orangeloginscreen.domain.usecase.ismoviefavourited

import com.example.orangeloginscreen.domain.model.Movie

interface IsMovieFavouritedUseCase {

    fun toggleFavouriteMovie(movie: Movie): Movie
}