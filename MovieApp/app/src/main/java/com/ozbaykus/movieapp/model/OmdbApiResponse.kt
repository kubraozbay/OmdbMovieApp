package com.ozbaykus.movieapp.model

import com.google.gson.annotations.SerializedName

data class OmdbApiResponse (
    @SerializedName("Response")
    val Response:Boolean,
    @SerializedName("Search")
    val Search:List<Search>,
    @SerializedName("totalResults")
    val totalResults:Int
)