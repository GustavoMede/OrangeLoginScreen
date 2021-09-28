package com.example.orangeloginscreen.dao

import com.example.orangeloginscreen.model.Movie

class MovieDao {

    companion object {
        private val movies = mutableListOf<Movie>()
    }

    fun generateListOfMovies() : List<Movie>{
        for (i in 1..100) {
            movies.add(Movie(url = "https://i.imgur.com/RoF2unX.jpeg"))
        }
        return movies.toList()
    }
}