package com.example.orangeloginscreen.data.source.localdatasource

import com.example.orangeloginscreen.domain.model.Movie
import kotlinx.coroutines.delay

class MovieLocalDataSourceImpl : MovieLocalDataSource {

    override fun find(idRecieved: String): Movie {
        return movies.find {
            idRecieved == it.id
        } ?: throw Exception("This movie doesn't exists")
    }

    override fun syncLists(remoteMovies: MutableList<Movie>) {
        if (!movies.containsAll(remoteMovies)) updateLocalMovies(remoteMovies)
    }

    override suspend fun listFavouriteMovies(): List<Movie> {
        delay(1000)
        return movies.filter { it.isFavourite }
    }

    override fun toggleFavouriteMovie(movie: Movie): Movie {
        return movies.find {
            it.id == movie.id
        }?.apply {
            isFavourite = !isFavourite
        } ?: throw Exception("Movie doesn't exists.")
    }

    override fun updateLocalMovies(remoteMovies: MutableList<Movie>) {
        val alreadyExistendMovies = mutableListOf<Movie>()
        for(i in remoteMovies.indices){
            movies.find {
                it.id == remoteMovies[i].id
            }?.apply {
                alreadyExistendMovies.add(this)
            }
        }
        remoteMovies.removeAll(alreadyExistendMovies)
        movies.addAll(remoteMovies)
    }


    override fun getAll(): List<Movie> {
        return movies
    }

    companion object {
        private val movies = mutableListOf<Movie>()
    }
}