package com.example.orangeloginscreen.presentation.ui.moviedetails

sealed class MovieDetailsState {

    object IDLE: MovieDetailsState()

    data class onError(val message: String): MovieDetailsState()
}