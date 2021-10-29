package com.example.orangeloginscreen.presentation.ui.favouritemovieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orangeloginscreen.domain.usecase.listfavouritemovies.ListFavouriteMovies
import kotlinx.coroutines.launch

class ListFavouriteMoviesViewModel(
    private val listFavouriteMoviesUseCase: ListFavouriteMovies
): ViewModel() {

    private val _viewState = MutableLiveData<ListFavouriteMoviesState>(ListFavouriteMoviesState.IDLE)

    val viewState: LiveData<ListFavouriteMoviesState> get() = _viewState

    fun listFavouriteMovies(){
        viewModelScope.launch {
            try {
                _viewState.value = ListFavouriteMoviesState.onLoading
                val movies = listFavouriteMoviesUseCase.listFavouriteMovies()
                _viewState.value = ListFavouriteMoviesState.onListLoaded(movies)
            }catch (e: Exception){
                _viewState.value = ListFavouriteMoviesState.onError(e.localizedMessage.orEmpty())
            }
        }
    }
}
