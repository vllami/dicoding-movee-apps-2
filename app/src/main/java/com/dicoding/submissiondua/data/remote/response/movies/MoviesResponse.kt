package com.dicoding.submissiondua.data.remote.response.movies

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results")
    val result: List<MoviesRemote>
)