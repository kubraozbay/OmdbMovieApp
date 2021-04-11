package com.ozbaykus.movieapp.di.component

import android.content.Context
import com.ozbaykus.movieapp.di.modules.AppModule
import com.ozbaykus.movieapp.di.modules.CoroutinesModule
import com.ozbaykus.movieapp.di.modules.NetworkModule
import com.ozbaykus.movieapp.di.modules.RepositoryModule
import com.ozbaykus.movieapp.ui.activity.MainActivity
import com.ozbaykus.movieapp.ui.movieDetail.MovieDetailFragment
import com.ozbaykus.movieapp.ui.searchMovie.SearchMovieFragment
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        CoroutinesModule::class
    ]
)
interface AppComponents {
    fun context(): Context

    fun retrofit(): Retrofit

    fun inject(mainActivity: MainActivity)

    fun inject(searchMovieFragment: SearchMovieFragment)

    fun inject(movieDetailFragment: MovieDetailFragment)
}