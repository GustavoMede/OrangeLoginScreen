package com.example.orangeloginscreen.domain.model.dao

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDao(
    val id: Int,
    val backdrop_path: String,
    val poster_path: String,
    val original_title: String,
    val overview: String,
    val vote_average: Double
) : Parcelable