package com.ozbaykus.movieapp.ui.movieDetail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ozbaykus.movieapp.MainApp
import com.ozbaykus.movieapp.R
import com.ozbaykus.movieapp.ui.searchMovie.MovieViewModel
import com.ozbaykus.movieapp.utils.hide
import com.ozbaykus.movieapp.utils.show
import com.ozbaykus.movieapp.utils.viewModelProvider
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.nested_scroll_view.*
import kotlinx.android.synthetic.main.table_cell_view.view.*
import javax.inject.Inject


class MovieDetailFragment : Fragment() {

    private val appComponents by lazy { MainApp.appComponents }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private fun getViewModel(): MovieViewModel {
        return viewModelProvider(viewModelFactory)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        appComponents.inject(this)
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)
        val imdbId = arguments!!.getString("imdbId") ?: ""
        getViewModel().getMovieDetail(imdbId)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().showLoading.observe(this, Observer { showLoading ->
            if (showLoading) {
                progressBarMovieDetail.show()
            } else {
                progressBarMovieDetail.hide()
                appBar.show()
            }
        })
        getViewModel().movieDetail.observe(this, Observer { response ->
            response?.takeIf { it.response == "True" }?.let {
                plot.text = it.plot
                year.key.text = "Year"
                genre.key.text = "Genre"
                runtime.key.text = "Runtime"
                director.key.text = "Director"
                writer.key.text = "Writer"

                year.value.text = it.year.toString()
                genre.value.text = it.genre
                runtime.value.text = it.runtime
                director.value.text = it.director
                writer.value.text = it.writer

                Glide.with(activity!!).load(it.poster).placeholder(R.drawable.board)
                    .into(expandedImage)
                collapsingToolbar.title = it.title

            }
        })
    }
}