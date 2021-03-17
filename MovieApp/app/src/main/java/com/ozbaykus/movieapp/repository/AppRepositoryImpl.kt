package com.ozbaykus.movieapp.repository

import com.ozbaykus.movieapp.api.API_KEY
import com.ozbaykus.movieapp.data.RemoteDataNotFoundException
import com.ozbaykus.movieapp.data.ResultData
import com.ozbaykus.movieapp.di.IoDispatcher
import com.ozbaykus.movieapp.model.OmdbApiResponse
import com.ozbaykus.movieapp.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher

class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AppRepository {
    override suspend fun movieSearch(
        movieName: String
    ): ResultData<OmdbApiResponse> {
        return when (val result = remoteDataSource.movieSearch(movieName, API_KEY)) {
            is ResultData.Success -> {
                ResultData.Success(result.data)
            }
            is ResultData.Error -> {
                ResultData.Error(RemoteDataNotFoundException())
            }
        }
    }
}