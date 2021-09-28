package com.example.orangeloginscreen.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.orangeloginscreen.dao.MovieDao
import com.example.orangeloginscreen.databinding.ActivityListMoviesBinding
import com.example.orangeloginscreen.model.Movie
import com.example.orangeloginscreen.model.User
import com.example.orangeloginscreen.ui.RecyclerView.adapter.ListMoviesAdapter
import com.example.orangeloginscreen.ui.RecyclerView.adapter.MovieListener

class ListMoviesActivity(

) : AppCompatActivity() {

    private val binding by lazy {
        ActivityListMoviesBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        MovieDao()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureAdapter()
    }


    private val oneMovieSelected = object : MovieListener {
        override fun invoke(movie: Movie) {
            TODO("Implement details on click")
        }
    }

    private fun configureAdapter() {
        binding.recyclerView.adapter = ListMoviesAdapter(
            dao.generateListOfMovies(), oneMovieSelected
        )
    }
}
