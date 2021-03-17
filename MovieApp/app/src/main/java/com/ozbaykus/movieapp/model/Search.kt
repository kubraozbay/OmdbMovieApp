package com.ozbaykus.movieapp.model

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("Title")
    val Title: String,
    @SerializedName("Year")
    val Year: String,
    @SerializedName("imdbID")
    val imdbID: String,
    @SerializedName("Type")
    val Type: String,
    @SerializedName("Poster")
    val Poster: String
)