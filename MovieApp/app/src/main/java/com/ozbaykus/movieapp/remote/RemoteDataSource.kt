package com.ozbaykus.movieapp.remote

import com.ozbaykus.movieapp.data.ResultData
import com.ozbaykus.movieapp.model.OmdbApiResponse

interface RemoteDataSource {
    suspend fun movieSearch(
        movieName: String,
        apikey: String
    ): ResultData<OmdbApiResponse>
}