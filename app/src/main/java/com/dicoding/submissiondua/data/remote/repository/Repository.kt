package com.dicoding.submissiondua.data.remote.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.submissiondua.data.entity.DetailsEntity
import com.dicoding.submissiondua.data.entity.MoviesEntity
import com.dicoding.submissiondua.data.entity.TvShowsEntity
import com.dicoding.submissiondua.data.remote.response.movies.MoviesDetailsResponse
import com.dicoding.submissiondua.data.remote.response.movies.MoviesRemote
import com.dicoding.submissiondua.data.remote.response.tv_shows.TvShowsDetailsResponse
import com.dicoding.submissiondua.data.remote.response.tv_shows.TvShowsRemote
import com.dicoding.submissiondua.data.remote.source.DataSource
import com.dicoding.submissiondua.data.remote.source.RemoteDataSource

class Repository private constructor(private val remoteDataSource: RemoteDataSource) : DataSource {

    companion object {
        @Volatile
        private var repository: Repository? = null

        fun getRepository(remoteDataSource: RemoteDataSource): Repository {
            return repository ?: synchronized(this) {
                Repository(remoteDataSource).apply {
                    repository = this
                }
            }
        }
    }

    private lateinit var detailsEntity: DetailsEntity

    override fun loadMovies(): LiveData<List<MoviesEntity>> {
        val getMovies = MutableLiveData<List<MoviesEntity>>()

        remoteDataSource.getMoviesList(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesList: List<MoviesRemote>?) {
                val moviesEntity = ArrayList<MoviesEntity>()

                if (moviesList != null) {
                    for (movies in moviesList) {
                        movies.apply {
                            val moviesEntities = MoviesEntity(
                                id,
                                poster,
                                title,
                                rating,
                                releaseDate
                            )

                            moviesEntity.add(moviesEntities)
                        }
                    }
                    getMovies.postValue(moviesEntity)
                }
            }
        })

        return getMovies
    }

    override fun loadMoviesDetails(moviesId: String): LiveData<DetailsEntity> {
        val getMoviesDetails = MutableLiveData<DetailsEntity>()

        remoteDataSource.getMoviesDetails(object : RemoteDataSource.LoadMoviesDetailsCallback {
            override fun onAllMoviesDetailsReceived(moviesDetails: MoviesDetailsResponse?) {
                moviesDetails?.apply {
                    ArrayList<String>().also {
                        for (genres in genre) {
                            it.add(genres.name)
                        }

                        detailsEntity = DetailsEntity(
                            id = id,
                            backdrop = backdrop,
                            poster = poster,
                            title = title,
                            rating = rating,
                            releaseDate = releaseDate,
                            genre = it,
                            synopsis = synopsis
                        )
                    }
                    getMoviesDetails.postValue(detailsEntity)
                }
            }
        }, moviesId)

        return getMoviesDetails
    }

    override fun loadTvShows(): LiveData<List<TvShowsEntity>> {
        val getTvShows = MutableLiveData<List<TvShowsEntity>>()

        remoteDataSource.getTvShowsList(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowsList: List<TvShowsRemote>?) {
                val tvShowsEntity = ArrayList<TvShowsEntity>()

                if (tvShowsList != null) {
                    for (tvShows in tvShowsList) {
                        tvShows.apply {
                            val tvShowsEntities = TvShowsEntity(
                                id,
                                poster,
                                title,
                                rating,
                                releaseDate
                            )

                            tvShowsEntity.add(tvShowsEntities)
                        }
                    }
                    getTvShows.postValue(tvShowsEntity)
                }
            }
        })

        return getTvShows
    }

    override fun loadTvShowsDetails(tvShowsId: String): LiveData<DetailsEntity> {
        val getTvShowsDetails = MutableLiveData<DetailsEntity>()

        remoteDataSource.getTvShowsDetails(object : RemoteDataSource.LoadTvShowsDetailsCallback {
            override fun onAllTvShowsDetailsReceived(tvShowsDetails: TvShowsDetailsResponse?) {
                tvShowsDetails?.apply {
                    ArrayList<String>().also {
                        for (genres in genre) {
                            it.add(genres.name)
                        }

                        detailsEntity = DetailsEntity(
                            id = id,
                            backdrop = backdrop,
                            poster = poster,
                            title = title,
                            rating = rating,
                            releaseDate = releaseDate,
                            genre = it,
                            synopsis = synopsis
                        )
                    }
                    getTvShowsDetails.postValue(detailsEntity)
                }
            }
        }, tvShowsId)

        return getTvShowsDetails
    }

}