package com.dicoding.submissiondua.data.entity

import com.google.gson.annotations.SerializedName

data class DetailsEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("backdrop_path")
    val backdrop: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("genres")
    val genre: List<String>,
    @SerializedName("overview")
    val synopsis: String
)