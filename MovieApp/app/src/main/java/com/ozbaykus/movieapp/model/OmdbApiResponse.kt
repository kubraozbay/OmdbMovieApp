package com.ozbaykus.movieapp.model

import com.google.gson.annotations.SerializedName

data class OmdbApiResponse (
    @SerializedName("Response")
    val response:Boolean,
    @SerializedName("Search")
    val search:List<Search>,
    @SerializedName("totalResults")
    val totalResults:Int
)