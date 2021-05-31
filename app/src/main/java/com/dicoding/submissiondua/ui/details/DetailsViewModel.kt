package com.dicoding.submissiondua.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submissiondua.data.entity.DetailsEntity
import com.dicoding.submissiondua.data.remote.repository.Repository

class DetailsViewModel(private val repository: Repository) : ViewModel() {

    private lateinit var detailsEntity: LiveData<DetailsEntity>

    companion object {
        const val MOVIES_DETAILS = "movies_details"
        const val TV_SHOWS_DETAILS = "tv_shows_details"
    }

    fun setSelected(id: String, selected: String) {
        repository.apply {
            when(selected) {
                MOVIES_DETAILS -> detailsEntity = loadMoviesDetails(id)
                TV_SHOWS_DETAILS -> detailsEntity = loadTvShowsDetails(id)
            }
        }
    }

    fun getSelected() = detailsEntity

}