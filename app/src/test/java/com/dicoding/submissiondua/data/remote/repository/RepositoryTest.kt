package com.dicoding.submissiondua.data.remote.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.submissiondua.data.remote.source.RemoteDataSource
import com.dicoding.submissiondua.utils.DetailsDummyData
import com.dicoding.submissiondua.utils.DummyData
import com.dicoding.submissiondua.utils.LiveDataTestUtil
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class RepositoryTest {

    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)
    private val repository = FakeRepository(remoteDataSource)

    private val moviesDummyData = DummyData.getMoviesRemote()
    private val tvShowsDummyData = DummyData.getTvShowsRemote()

    private val moviesId = moviesDummyData[0].id.toString()
    private val tvShowsId = tvShowsDummyData[0].id.toString()

    private val moviesDetailsDummyData = DetailsDummyData.getMoviesDetailsRemote()
    private val tvShowsDetailsDummyData = DetailsDummyData.getTvShowsDetailsRemote()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getMoviesList() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadMoviesCallback).onAllMoviesReceived(moviesDummyData)
            null
        }.`when`(remoteDataSource).getMoviesList(any())

        LiveDataTestUtil.getValue(repository.loadMovies()).also {
            verify(remoteDataSource).getMoviesList(any())

            assertThat(it.size).isNotNull()
            assertThat(it.size.toLong()).isEqualTo(moviesDummyData.size.toLong())
        }
    }

    @Test
    fun getMoviesDetails() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadMoviesDetailsCallback).onAllMoviesDetailsReceived(moviesDetailsDummyData)
            null
        }.`when`(remoteDataSource).getMoviesDetails(any(), eq(moviesId))

        LiveDataTestUtil.getValue(repository.loadMoviesDetails(moviesId)).also {
            verify(remoteDataSource).getMoviesDetails(any(), eq(moviesId))

            assertThat(it).isNotNull()
            assertThat(it.id).isEqualTo(moviesDetailsDummyData.id)
        }
    }

    @Test
    fun getTvShowsList() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadTvShowsCallback).onAllTvShowsReceived(tvShowsDummyData)
            null
        }.`when`(remoteDataSource).getTvShowsList(any())

        LiveDataTestUtil.getValue(repository.loadTvShows()).also {
            verify(remoteDataSource).getTvShowsList(any())

            assertThat(it.size).isNotNull()
            assertThat(it.size.toLong()).isEqualTo(tvShowsDummyData.size.toLong())
        }
    }

    @Test
    fun getTvShowsDetails() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadTvShowsDetailsCallback).onAllTvShowsDetailsReceived(tvShowsDetailsDummyData)
            null
        }.`when`(remoteDataSource).getTvShowsDetails(any(), eq(tvShowsId))

        LiveDataTestUtil.getValue(repository.loadTvShowsDetails(tvShowsId)).also {
            verify(remoteDataSource).getTvShowsDetails(any(), eq(tvShowsId))

            assertThat(it).isNotNull()
            assertThat(it.id).isEqualTo(tvShowsDetailsDummyData.id)
        }
    }

}