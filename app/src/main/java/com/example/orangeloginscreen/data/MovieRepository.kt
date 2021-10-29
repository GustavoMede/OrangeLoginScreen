package com.example.orangeloginscreen.data

import com.example.orangeloginscreen.data.source.IMAGE_URL
import com.example.orangeloginscreen.data.source.localdatasource.MovieLocalDataSource
import com.example.orangeloginscreen.data.source.remotedatasource.MovieRemoteDataSource
import com.example.orangeloginscreen.domain.model.Movie

class MovieRepository(
    private val moviesLocal: MovieLocalDataSource,
    private val moviesRemote: MovieRemoteDataSource
) {

    suspend fun getMovies(): List<Movie> {
        val movies = moviesRemote.listAll().getMovies().map {
            Movie(
                id = it.id.toString(),
                urlImage = IMAGE_URL + it.backdrop_path,
                urlBanner = IMAGE_URL + it.poster_path,
                name = it.original_title,
                rate = it.vote_average.toString(),
                synopsis = it.overview
            )
        }
        moviesLocal.syncLists(movies.toMutableList())
        return moviesLocal.getAll().toList()
    }

    suspend fun listFavouriteMovies(): List<Movie> {
        return moviesLocal.listFavouriteMovies()
    }

    fun toggleFavouriteMovie(movie: Movie): Movie {
        return moviesLocal.toggleFavouriteMovie(movie)
    }
}