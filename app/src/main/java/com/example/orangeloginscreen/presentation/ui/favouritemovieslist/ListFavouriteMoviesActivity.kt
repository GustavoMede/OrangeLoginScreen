package com.example.orangeloginscreen.presentation.ui.favouritemovieslist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.orangeloginscreen.RecyclerView.adapter.FavouriteMovieListener
import com.example.orangeloginscreen.RecyclerView.adapter.ListFavouriteMoviesAdapter
import com.example.orangeloginscreen.databinding.ActivityListFavouriteMoviesBinding
import com.example.orangeloginscreen.domain.model.Movie
import com.example.orangeloginscreen.presentation.ui.loadingdialog.LoadingDialog
import com.example.orangeloginscreen.presentation.ui.moviedetails.MovieDetailsActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFavouriteMoviesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListFavouriteMoviesBinding.inflate(layoutInflater)
    }

    private val listFavouriteMoviesViewModel: ListFavouriteMoviesViewModel by viewModel()

    private val loadingDialog by lazy {
        LoadingDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureToolbar()
        listFavouriteMovies()
        onObeserverState()
    }

    private fun listFavouriteMovies() {
        listFavouriteMoviesViewModel.listFavouriteMovies()
    }

    private fun configureToolbar(){
        setSupportActionBar(binding.favouriteMoviesToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun onObeserverState() {
        listFavouriteMoviesViewModel.viewState.observe(this) {
            when (it) {
                is ListFavouriteMoviesState.IDLE -> {
                }
                is ListFavouriteMoviesState.onError -> showError(it.message)
                is ListFavouriteMoviesState.onListLoaded -> onListLoaded(it.movies)
                is ListFavouriteMoviesState.onLoading -> onLoading()
                else -> {
                }
            }
        }
    }

    private fun onLoading() {
        loadingDialog.startLoadingDialog("Loading favourite list...")
    }

    private fun onListLoaded(movies: List<Movie>) {
        loadingDialog.finalizeLoadingDialog()
        configureAdapter(movies)
    }

    private val oneMovieSelected = object : FavouriteMovieListener {
        override fun invoke(movie: Movie) {
            startActivity(Intent(this@ListFavouriteMoviesActivity, MovieDetailsActivity::class.java).apply {
                putExtra(Movie::javaClass.name, movie)
            })
        }
    }

    private fun configureAdapter(movies: List<Movie>) {
        binding.recyclerView.adapter = ListFavouriteMoviesAdapter(movies, oneMovieSelected)
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}