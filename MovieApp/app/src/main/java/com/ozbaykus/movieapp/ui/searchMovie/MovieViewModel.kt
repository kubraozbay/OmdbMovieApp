package com.ozbaykus.movieapp.ui.searchMovie

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozbaykus.movieapp.data.ResultData
import com.ozbaykus.movieapp.model.MovieDetail
import com.ozbaykus.movieapp.model.OmdbApiResponse
import com.ozbaykus.movieapp.repository.AppRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val repositoryImpl: AppRepositoryImpl
) : ViewModel() {

    private var _resultSearch = MutableLiveData<OmdbApiResponse>()
    var resultSearch: LiveData<OmdbApiResponse> = _resultSearch

    private var _movieDetail = MutableLiveData<MovieDetail>()
    var movieDetail: LiveData<MovieDetail> = _movieDetail

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    fun searchMovieByName(movieName: String) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            try {
                when (val response = repositoryImpl.movieSearch(movieName)) {
                    is ResultData.Success -> {
                        _showLoading.postValue(false)
                        _resultSearch.postValue(response.data)
                    }
                    is ResultData.Error -> {
                        _showLoading.postValue(false)
                        _errorMessage.postValue(response.exception.toString())
                    }
                }
            } catch (e: Exception) {
                _showLoading.postValue(false)
                _errorMessage.postValue(e.message)
            }
        }
    }

    fun getMovieDetail(imdbId: String) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            try {
                when (val response = repositoryImpl.movieDetail(imdbId)) {
                    is ResultData.Success -> {
                        _showLoading.postValue(false)
                        _movieDetail.postValue(response.data)
                    }
                    is ResultData.Error -> {
                        _showLoading.postValue(false)
                        _errorMessage.postValue(response.exception.toString())
                    }
                }
            } catch (e: Exception) {
                _showLoading.postValue(false)
                _errorMessage.postValue(e.message)
            }
        }
    }
}