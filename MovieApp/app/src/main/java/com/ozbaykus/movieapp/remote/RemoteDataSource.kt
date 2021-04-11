package com.ozbaykus.movieapp.remote

import com.ozbaykus.movieapp.data.ResultData
import com.ozbaykus.movieapp.model.MovieDetail
import com.ozbaykus.movieapp.model.OmdbApiResponse

interface RemoteDataSource {
    suspend fun movieSearch(
        movieName: String,
        apikey: String
    ): ResultData<OmdbApiResponse>

    suspend fun movieDetail(
        imdbId: String,
        apikey: String
    ): ResultData<MovieDetail>
}