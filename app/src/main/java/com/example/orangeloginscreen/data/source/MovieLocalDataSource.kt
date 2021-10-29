package com.example.orangeloginscreen.data.source

import com.example.orangeloginscreen.domain.model.Movie

interface MovieLocalDataSource {

    suspend fun add(recievedMovies: MutableList<Movie>)

    fun find(idRecieved: String): Movie?

    suspend fun listAll(): List<Movie>

    fun clear()
}