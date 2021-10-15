package com.example.orangeloginscreen.data.source

import com.example.orangeloginscreen.domain.model.Movie
import kotlinx.coroutines.delay

class MovieLocalDataSourceImpl: MovieLocalDataSource {

    override suspend fun add(recievedMovies: MutableList<Movie>){
        movies.addAll(recievedMovies)
    }

    override fun find(idRecieved: String): Movie? {
        for(i in movies.indices){
            if(movies[i].id.equals(idRecieved)){
                return movies[i]
            }
        }
        return null
    }

    override suspend fun listAll(): List<Movie>{
        delay(1000)
        return movies.toList()
    }

    override fun clear(){
        movies.clear()
    }

    companion object {
        private val movies = mutableListOf<Movie>()
    }
}