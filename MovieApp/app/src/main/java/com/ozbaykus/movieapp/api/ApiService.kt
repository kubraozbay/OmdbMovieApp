package com.ozbaykus.movieapp.api

import com.ozbaykus.movieapp.model.OmdbApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun getMovies(
        @Query("s") movieName: String,
        @Query("apikey") apikey: String
    ): OmdbApiResponse
}