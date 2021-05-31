package com.dicoding.submissiondua.data.entity

import com.google.gson.annotations.SerializedName

data class MoviesEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("release_date")
    val releaseDate: String
)