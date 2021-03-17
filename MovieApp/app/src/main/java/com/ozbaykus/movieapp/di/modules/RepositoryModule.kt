package com.ozbaykus.movieapp.di.modules

import com.ozbaykus.movieapp.api.ApiService
import com.ozbaykus.movieapp.di.IoDispatcher
import com.ozbaykus.movieapp.remote.RemoteDataSourceImpl
import com.ozbaykus.movieapp.repository.AppRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAppRepository(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        api: ApiService
    ): AppRepositoryImpl {
        val userDataSource = RemoteDataSourceImpl(api, ioDispatcher)
        return AppRepositoryImpl(userDataSource, ioDispatcher)
    }
}