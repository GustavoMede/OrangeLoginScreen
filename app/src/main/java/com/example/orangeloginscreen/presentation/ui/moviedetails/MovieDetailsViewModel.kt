package com.example.orangeloginscreen.presentation.ui.moviedetails

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orangeloginscreen.domain.model.Movie
import com.example.orangeloginscreen.domain.usecase.ismoviefavourited.IsMovieFavouritedUseCase
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val isMovieFavouritedUseCase: IsMovieFavouritedUseCase
) : ViewModel() {

    private val _movieState = MutableLiveData<Movie>()
    val movieState: LiveData<Movie> get() = _movieState

    private val _viewState = MutableLiveData<MovieDetailsState>(MovieDetailsState.IDLE)
    val viewState: LiveData<MovieDetailsState> get() = _viewState

    fun toggleFavouriteMovie() {
        viewModelScope.launch {
            _movieState.value?.let {
                try {
                    val movieResponse = isMovieFavouritedUseCase.toggleFavouriteMovie(it)
                    _movieState.value = movieResponse
                }catch (e: Exception){
                    _viewState.value = MovieDetailsState.onError(e.localizedMessage.orEmpty())
                }
            }
        }
    }

    fun initByIntent(intent: Intent){
        _movieState.value = intent.getParcelableExtra(Movie::javaClass.name)
    }
}