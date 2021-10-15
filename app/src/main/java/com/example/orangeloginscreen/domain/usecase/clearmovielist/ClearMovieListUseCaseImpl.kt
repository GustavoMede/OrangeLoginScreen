package com.example.orangeloginscreen.domain.usecase.clearmovielist

import com.example.orangeloginscreen.data.MovieRepository

class ClearMovieListUseCaseImpl(
    private val repository: MovieRepository
): ClearMovieListUseCase {

    override fun clearMovieList(){
        repository.clearMovieList()
    }
}