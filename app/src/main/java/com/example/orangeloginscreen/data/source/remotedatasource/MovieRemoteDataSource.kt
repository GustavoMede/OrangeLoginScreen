package com.example.orangeloginscreen.data.source.remotedatasource

import com.example.orangeloginscreen.domain.model.requests.MovieResponse

interface MovieRemoteDataSource {

    suspend fun listAll(): MovieResponse
}