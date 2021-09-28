package com.example.orangeloginscreen.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class User(
    val id : String = UUID.randomUUID().toString(),
    var username : String,
    var password : String
) //Parcelable implement
    : Parcelable