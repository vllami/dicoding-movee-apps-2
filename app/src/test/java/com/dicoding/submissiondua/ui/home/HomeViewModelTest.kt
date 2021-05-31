package com.dicoding.submissiondua.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.submissiondua.data.entity.MoviesEntity
import com.dicoding.submissiondua.data.entity.TvShowsEntity
import com.dicoding.submissiondua.data.remote.repository.Repository
import com.dicoding.submissiondua.utils.DummyData
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var moviesObserver: Observer<List<MoviesEntity>>

    @Mock
    private lateinit var tvShowsObserver: Observer<List<TvShowsEntity>>

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(repository)
    }

    @Test
    fun getMovies() {
        val moviesDummyData = DummyData.getMovies()
        with(moviesDummyData) {
            MutableLiveData<List<MoviesEntity>>().also {
                it.value = this
                `when`(repository.loadMovies()).thenReturn(it)
            }

            homeViewModel.getMovies().apply {
                value.also {
                    verify(repository).loadMovies()

                    assertThat(it).isNotNull()
                    assertThat(it?.size).isEqualTo(3)
                }
                observeForever(moviesObserver)
            }

            verify(moviesObserver).onChanged(this)
        }
    }

    @Test
    fun getTvShows() {
        val tvShowsDummyData = DummyData.getTvShows()
        with(tvShowsDummyData) {
            MutableLiveData<List<TvShowsEntity>>().also {
                it.value = this
                `when`(repository.loadTvShows()).thenReturn(it)
            }

            homeViewModel.getTvShows().apply {
                value.also {
                    verify(repository).loadTvShows()

                    assertThat(it).isNotNull()
                    assertThat(it?.size).isEqualTo(3)
                }
                observeForever(tvShowsObserver)
            }

            verify(tvShowsObserver).onChanged(this)
        }
    }

}