package com.example.orangeloginscreen.model

import java.util.*

data class Movie(
    val id: String = UUID.randomUUID().toString(),
    val url: String
)