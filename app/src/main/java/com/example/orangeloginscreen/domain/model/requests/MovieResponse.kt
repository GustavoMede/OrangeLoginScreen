package com.example.orangeloginscreen.domain.model.requests

import android.os.Parcelable
import com.example.orangeloginscreen.domain.model.Movie
import com.example.orangeloginscreen.domain.model.dao.MovieDao
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    private val results: List<MovieDao>
) : Parcelable {

    fun getMovies(): List<MovieDao>{
        return results.toList()
    }
}