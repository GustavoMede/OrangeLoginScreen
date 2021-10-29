package com.example.orangeloginscreen.data.source.remotedatasource

import com.example.orangeloginscreen.data.source.API_KEY
import com.example.orangeloginscreen.domain.model.requests.MovieResponse

class MovieRemoteDataSourceImpl: MovieRemoteDataSource {

    override suspend fun listAll(): MovieResponse {
        return Repository.getData().getMovies(API_KEY)
    }

}