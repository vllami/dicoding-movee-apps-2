package com.dicoding.submissiondua.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.submissiondua.data.entity.DetailsEntity
import com.dicoding.submissiondua.data.remote.repository.Repository
import com.dicoding.submissiondua.ui.details.DetailsViewModel.Companion.MOVIES_DETAILS
import com.dicoding.submissiondua.ui.details.DetailsViewModel.Companion.TV_SHOWS_DETAILS
import com.dicoding.submissiondua.utils.DetailsDummyData
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
class DetailsViewModelTest {

    private lateinit var detailsViewModel: DetailsViewModel

    private val moviesDetailsDummyData = DetailsDummyData.getMoviesDetails()
    private val moviesId = moviesDetailsDummyData.id.toString()

    private val tvShowsDetailsDummyData = DetailsDummyData.getTvShowsDetails()
    private val tvShowsId = tvShowsDetailsDummyData.id.toString()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var moviesObserver: Observer<DetailsEntity>

    @Mock
    private lateinit var tvShowsObserver: Observer<DetailsEntity>

    @Before
    fun setUp() {
        detailsViewModel = DetailsViewModel(repository)
    }

    @Test
    fun getMoviesDetails() {
        MutableLiveData<DetailsEntity>().also {
            it.value = moviesDetailsDummyData
            `when`(repository.loadMoviesDetails(moviesId)).thenReturn(it)
        }

        detailsViewModel.apply {
            setSelected(moviesId, MOVIES_DETAILS)

            getSelected().apply {
                value.also {
                    verify(repository).loadMoviesDetails(moviesId)
                    assertThat(it).isNotNull()

                    moviesDetailsDummyData.apply {
                        assertThat(it?.id).isEqualTo(id)
                        assertThat(it?.backdrop).isEqualTo(backdrop)
                        assertThat(it?.poster).isEqualTo(poster)
                        assertThat(it?.title).isEqualTo(title)
                        assertThat(it?.rating).isEqualTo(rating)
                        assertThat(it?.releaseDate).isEqualTo(releaseDate)
                        assertThat(it?.genre).isEqualTo(genre)
                        assertThat(it?.synopsis).isEqualTo(synopsis)
                    }
                }

                observeForever(moviesObserver)
            }
        }

        verify(moviesObserver).onChanged(moviesDetailsDummyData)
    }

    @Test
    fun getTvShowsDetails() {
        MutableLiveData<DetailsEntity>().also {
            it.value = tvShowsDetailsDummyData
            `when`(repository.loadTvShowsDetails(tvShowsId)).thenReturn(it)
        }

        detailsViewModel.apply {
            setSelected(tvShowsId, TV_SHOWS_DETAILS)

            getSelected().apply {
                value.also {
                    verify(repository).loadTvShowsDetails(tvShowsId)
                    assertThat(it).isNotNull()

                    tvShowsDetailsDummyData.apply {
                        assertThat(it?.id).isEqualTo(id)
                        assertThat(it?.backdrop).isEqualTo(backdrop)
                        assertThat(it?.poster).isEqualTo(poster)
                        assertThat(it?.title).isEqualTo(title)
                        assertThat(it?.rating).isEqualTo(rating)
                        assertThat(it?.releaseDate).isEqualTo(releaseDate)
                        assertThat(it?.genre).isEqualTo(genre)
                        assertThat(it?.synopsis).isEqualTo(synopsis)
                    }
                }

                observeForever(tvShowsObserver)
            }
        }

        verify(tvShowsObserver).onChanged(tvShowsDetailsDummyData)
    }

}