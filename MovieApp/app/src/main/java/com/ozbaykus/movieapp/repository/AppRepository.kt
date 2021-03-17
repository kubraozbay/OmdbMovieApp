package com.ozbaykus.movieapp.repository

import com.ozbaykus.movieapp.data.ResultData
import com.ozbaykus.movieapp.model.OmdbApiResponse

interface AppRepository {
    suspend fun movieSearch(
        movieName: String
    ): ResultData<OmdbApiResponse>
}