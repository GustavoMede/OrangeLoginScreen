package com.example.orangeloginscreen.presentation.ui.listmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orangeloginscreen.domain.usecase.clearmovielist.ClearMovieListUseCase
import com.example.orangeloginscreen.domain.usecase.createmovies.CreateMoviesUseCase
import com.example.orangeloginscreen.domain.usecase.getmovies.GetMoviesUseCase
import kotlinx.coroutines.launch

class ListMoviesViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val createMoviesUseCase: CreateMoviesUseCase,
    private val clearMovieListUseCase: ClearMovieListUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<ListMoviesState>(ListMoviesState.IDLE)

    val viewState: LiveData<ListMoviesState> get() = _viewState

    fun createMovies() {
        viewModelScope.launch {
            try {
                _viewState.value = ListMoviesState.onLoading
                createMoviesUseCase.createMovies()
                _viewState.value = ListMoviesState.onListCreated
            } catch (e: Exception) {
                _viewState.value = ListMoviesState.onError(e.localizedMessage.orEmpty())
            }
        }
    }

    fun listAllMovies() {
        viewModelScope.launch {
            try {
                _viewState.value = ListMoviesState.onLoading
                val movies = getMoviesUseCase.getMovies()
                _viewState.value = ListMoviesState.onListLoaded(movies)
            } catch (e: Exception) {
                _viewState.value = ListMoviesState.onError(e.localizedMessage.orEmpty())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        clearMovieListUseCase.clearMovieList()
    }

    init {
        createMovies()
    }
}