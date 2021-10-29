package com.example.orangeloginscreen.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Movie(
    val id: String,
    val urlImage: String,
    val urlBanner: String,
    val name: String,
    val rate: String,
    val synopsis: String,
    var isFavourite: Boolean = false
) : Parcelable