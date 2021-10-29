package com.example.orangeloginscreen.presentation.ui.moviedetails

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.orangeloginscreen.databinding.ActivityMovieDetailsBinding
import com.example.orangeloginscreen.domain.model.Movie
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMovieDetailsBinding.inflate(layoutInflater)
    }

    private val favouriteBtn by lazy {
        binding.movieDetailsFavouriteBtn
    }

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onObserverState()
        onObserverMovie()
        configureFavouriteButton()
        movieDetailsViewModel.initByIntent(intent)
    }

    private fun selectedMovieBind(movie: Movie?) {
        movie?.let {
            configureToolbar(it)
            verifyFavouriteMovie(it)
            Glide.with(this).load(it.urlImage).into(binding.movieDetailsImage)
            binding.movieDetailsRate.text = it.rate
            binding.movieDetailsSynopsis.text = it.synopsis
        }
    }

    private fun configureToolbar(movie: Movie?) {
        setSupportActionBar(binding.movieDetailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = movie?.name
    }

    private fun configureFavouriteButton() {
        favouriteBtn.setOnClickListener {
            onFavClick()
        }
    }

    private fun onObserverState() {
        movieDetailsViewModel.viewState.observe(this) {
            when (it) {
                is MovieDetailsState.onError -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun onObserverMovie() {
        movieDetailsViewModel.movieState.observe(this) {
            selectedMovieBind(it)
        }
    }

    private fun verifyFavouriteMovie(movie: Movie) {
        if (movie.isFavourite && !favouriteBtn.isAnimating) {
            favouriteBtn.progress = 1F
        } else if (!movie.isFavourite) {
            favouriteBtn.progress = 0F
            favouriteBtn.cancelAnimation()
        }
    }

    private fun onFavClick() {
        favouriteBtn.playAnimation()
        movieDetailsViewModel.toggleFavouriteMovie()
    }
}