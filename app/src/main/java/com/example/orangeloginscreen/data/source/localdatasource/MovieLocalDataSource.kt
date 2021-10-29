package com.example.orangeloginscreen.data.source.localdatasource

import com.example.orangeloginscreen.domain.model.Movie
import com.example.orangeloginscreen.domain.model.requests.MovieResponse

interface MovieLocalDataSource {

    fun find(idRecieved: String): Movie

    fun syncLists(remoteMovies: MutableList<Movie>)

    suspend fun listFavouriteMovies(): List<Movie>

    fun toggleFavouriteMovie(movie: Movie): Movie

    fun updateLocalMovies(remoteMovies: MutableList<Movie>)

    fun getAll(): List<Movie>
}