package com.ozbaykus.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ozbaykus.movieapp.MainApp
import com.ozbaykus.movieapp.R
import com.ozbaykus.movieapp.ui.searchMovie.SearchMovieFragment
import com.ozbaykus.movieapp.utils.replaceFragment

class MainActivity : AppCompatActivity() {

    private val appComponents by lazy { MainApp.appComponents }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponents.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(SearchMovieFragment(),R.id.frame_layout_container)
    }
}