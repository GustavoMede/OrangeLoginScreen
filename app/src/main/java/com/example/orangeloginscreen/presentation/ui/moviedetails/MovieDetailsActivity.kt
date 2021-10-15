package com.example.orangeloginscreen.presentation.ui.moviedetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.orangeloginscreen.databinding.ActivityMovieDetailsBinding
import com.example.orangeloginscreen.domain.model.Movie

class MovieDetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMovieDetailsBinding.inflate(layoutInflater)
    }

    private val movie by lazy { intent.getParcelableExtra<Movie>(Movie::javaClass.name) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        selectedMovieBind()
        configureToolbar()
    }

    private fun selectedMovieBind(){
        movie?.let {
            Glide.with(this).load(it.urlImage).into(binding.movieDetailsImage)
            binding.movieDetailsRate.text = it.rate
            binding.movieDetailsSynopsis.text = it.synopsis
        }
    }

    private fun configureToolbar(){
        setSupportActionBar(binding.movieDetailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = movie?.name
    }
}