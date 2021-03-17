package com.ozbaykus.movieapp.ui.searchMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozbaykus.movieapp.data.ResultData
import com.ozbaykus.movieapp.model.OmdbApiResponse
import com.ozbaykus.movieapp.repository.AppRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val repositoryImpl: AppRepositoryImpl
) : ViewModel() {

    private var _resultSearch= MutableLiveData<OmdbApiResponse>()
    var resultSearch: LiveData<OmdbApiResponse> = _resultSearch

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    fun searchMovieByName(movieName:String) {
        viewModelScope.launch {
            try {
                when (val response = repositoryImpl.movieSearch(movieName)) {
                    is ResultData.Success -> {
                        _resultSearch.postValue(response.data)
                    }
                    is ResultData.Error -> {
                        _errorMessage.postValue(response.exception.toString())
                    }
                }
            } catch (e: Exception) {

                _errorMessage.postValue(e.message)
            }
        }
    }
}