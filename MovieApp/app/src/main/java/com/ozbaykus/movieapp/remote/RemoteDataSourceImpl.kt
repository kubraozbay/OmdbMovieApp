package com.ozbaykus.movieapp.remote

import com.ozbaykus.movieapp.api.ApiService
import com.ozbaykus.movieapp.data.ResultData
import com.ozbaykus.movieapp.di.IoDispatcher
import com.ozbaykus.movieapp.model.OmdbApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(
    private val api: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RemoteDataSource {

    override suspend fun movieSearch(
        movieName: String,
        apikey: String
    ): ResultData<OmdbApiResponse> = withContext(ioDispatcher) {
            val request = api.getMovies(movieName, apikey)
            ResultData.Success(request)
        }

}