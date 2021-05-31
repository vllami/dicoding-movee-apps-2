package com.dicoding.submissiondua.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submissiondua.data.entity.MoviesEntity
import com.dicoding.submissiondua.data.entity.TvShowsEntity
import com.dicoding.submissiondua.data.remote.repository.Repository

class HomeViewModel(private val repository: Repository) : ViewModel() {

    fun getMovies(): LiveData<List<MoviesEntity>> = repository.loadMovies()

    fun getTvShows(): LiveData<List<TvShowsEntity>> = repository.loadTvShows()

}