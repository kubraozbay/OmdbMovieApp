package com.ozbaykus.movieapp.ui.searchMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ozbaykus.movieapp.MainApp
import com.ozbaykus.movieapp.R
import com.ozbaykus.movieapp.utils.viewModelProvider
import kotlinx.android.synthetic.main.fragment_search_movie.*
import kotlinx.android.synthetic.main.fragment_search_movie.view.*
import javax.inject.Inject

class SearchMovieFragment : Fragment() {

    private val appComponents by lazy { MainApp.appComponents }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private fun getViewModel(): MovieViewModel {
        return viewModelProvider(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        appComponents.inject(this)

        val view = inflater.inflate(R.layout.fragment_search_movie, container, false)

        view.buttonSend.setOnClickListener {
            getViewModel().searchMovieByName("avengers")
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       getViewModel().resultSearch.observe(this, Observer { response ->
           response?.let {
               tvResult.text= it.Search[0].Year
           }
       })
    }

}