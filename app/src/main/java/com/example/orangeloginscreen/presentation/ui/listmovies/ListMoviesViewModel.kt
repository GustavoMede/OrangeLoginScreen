package com.example.orangeloginscreen.presentation.ui.listmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orangeloginscreen.domain.usecase.getmovies.GetMoviesUseCase
import kotlinx.coroutines.launch

class ListMoviesViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    private val _viewState = MutableLiveData<ListMoviesState>(ListMoviesState.IDLE)

    val viewState: LiveData<ListMoviesState> get() = _viewState

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
}