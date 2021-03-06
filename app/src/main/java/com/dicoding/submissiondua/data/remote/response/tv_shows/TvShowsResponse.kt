package com.dicoding.submissiondua.data.remote.response.tv_shows

import com.google.gson.annotations.SerializedName

data class TvShowsResponse(
    @SerializedName("results")
    val result: List<TvShowsRemote>
)