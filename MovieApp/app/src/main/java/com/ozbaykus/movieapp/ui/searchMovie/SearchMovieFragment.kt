package com.ozbaykus.movieapp.ui.searchMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ozbaykus.movieapp.MainApp
import com.ozbaykus.movieapp.R
import com.ozbaykus.movieapp.model.Search
import com.ozbaykus.movieapp.utils.viewModelProvider
import kotlinx.android.synthetic.main.fragment_search_movie.*
import kotlinx.android.synthetic.main.fragment_search_movie.view.*
import javax.inject.Inject

class SearchMovieFragment : Fragment() {

    private val appComponents by lazy { MainApp.appComponents }
    private lateinit var searchMovieAdapter: SearchMovieAdapter

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
            getViewModel().searchMovieByName("summer")
            it.visibility=View.GONE
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel().resultSearch.observe(this, Observer { response ->
            response?.let {
                searchMovieAdapter = SearchMovieAdapter(response.search)
                recyclerViewMovies.adapter = searchMovieAdapter
                searchMovieAdapter.setOnItemClickListener(object :
                    SearchMovieAdapter.SearchMovieViewHolder.SearchMovieItemClickListener {
                    override fun onItemClick(search: Search) {
                        Toast.makeText(activity, search.title, Toast.LENGTH_LONG).show()
                    }

                })
                recyclerViewMovies.layoutManager = GridLayoutManager(activity, 2)
            }
        })
    }


}