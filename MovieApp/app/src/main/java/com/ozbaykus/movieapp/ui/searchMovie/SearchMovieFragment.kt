package com.ozbaykus.movieapp.ui.searchMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ozbaykus.movieapp.MainApp
import com.ozbaykus.movieapp.R
import com.ozbaykus.movieapp.model.Search
import com.ozbaykus.movieapp.ui.movieDetail.MovieDetailFragment
import com.ozbaykus.movieapp.utils.hide
import com.ozbaykus.movieapp.utils.hideKeyboard
import com.ozbaykus.movieapp.utils.show
import com.ozbaykus.movieapp.utils.viewModelProvider
import kotlinx.android.synthetic.main.fragment_search_movie.*
import kotlinx.android.synthetic.main.fragment_search_movie.view.*
import kotlinx.android.synthetic.main.row_search_box.*
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
            it.hideKeyboard()
            if (!editTextSearchBox.text.toString().isNullOrBlank()) {
                getViewModel().searchMovieByName(editTextSearchBox.text.toString())
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().showLoading.observe(this, Observer { showLoading ->
            recyclerViewMovies.hide()
            if (showLoading) progressBar.show()
            else progressBar.hide()
        })

        getViewModel().resultSearch.observe(this, Observer { response ->
            response?.takeIf { it.response }?.let {
                searchMovieAdapter = SearchMovieAdapter(response.search)
                recyclerViewMovies.adapter = searchMovieAdapter
                searchMovieAdapter.setOnItemClickListener(object :
                    SearchMovieAdapter.SearchMovieViewHolder.SearchMovieItemClickListener {
                    override fun onItemClick(search: Search) {
                        val fragment = MovieDetailFragment()
                        val args = Bundle()
                        args.putString("imdbId", search.imdbID)
                        fragment.arguments = args
                        activity!!.supportFragmentManager.beginTransaction()
                            .add(R.id.frame_layout_container, fragment).addToBackStack(null)
                            .commit()
                    }
                })
                recyclerViewMovies.layoutManager = GridLayoutManager(activity, 2)
                textViewNoResult.hide()
                recyclerViewMovies.show()
            } ?: kotlin.run {
                textViewNoResult.text =
                    getString(R.string.error_no_result, editTextSearchBox.text.toString())
                textViewNoResult.show()
                recyclerViewMovies.hide()
            }
        })
    }
}