package com.dicoding.submissiondua.data.remote.source

import androidx.lifecycle.LiveData
import com.dicoding.submissiondua.data.entity.DetailsEntity
import com.dicoding.submissiondua.data.entity.MoviesEntity
import com.dicoding.submissiondua.data.entity.TvShowsEntity

interface DataSource {

    fun loadMovies(): LiveData<List<MoviesEntity>>

    fun loadMoviesDetails(moviesId: String): LiveData<DetailsEntity>

    fun loadTvShows(): LiveData<List<TvShowsEntity>>

    fun loadTvShowsDetails(tvShowsId: String): LiveData<DetailsEntity>

}