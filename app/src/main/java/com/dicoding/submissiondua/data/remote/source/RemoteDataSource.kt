package com.dicoding.submissiondua.data.remote.source

import android.util.Log
import com.dicoding.submissiondua.data.remote.response.movies.MoviesDetailsResponse
import com.dicoding.submissiondua.data.remote.response.movies.MoviesRemote
import com.dicoding.submissiondua.data.remote.response.movies.MoviesResponse
import com.dicoding.submissiondua.data.remote.response.tv_shows.TvShowsDetailsResponse
import com.dicoding.submissiondua.data.remote.response.tv_shows.TvShowsRemote
import com.dicoding.submissiondua.data.remote.response.tv_shows.TvShowsResponse
import com.dicoding.submissiondua.utils.EspressoIdlingResource.decrement
import com.dicoding.submissiondua.utils.EspressoIdlingResource.increment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.dicoding.submissiondua.retrofit.TMDBClient.Companion.getService as TMDBClientGetService

class RemoteDataSource {

    companion object {
        const val TAG = "RemoteDataSource"

        @Volatile
        private var remoteDataSource: RemoteDataSource? = null

        fun getRemoteDataSource(): RemoteDataSource {
            return remoteDataSource ?: synchronized(this) {
                RemoteDataSource().apply {
                    remoteDataSource = this
                }
            }
        }
    }

    fun getMoviesList(callback: LoadMoviesCallback) {
        increment()

        TMDBClientGetService().getMoviesList(1).enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                callback.onAllMoviesReceived(response.body()?.result)

                decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, throwable: Throwable) {
                Log.e(TAG, "Failure ${throwable.message}")

                decrement()
            }
        })
    }

    fun getMoviesDetails(callback: LoadMoviesDetailsCallback, id: String) {
        increment()

        TMDBClientGetService().getMoviesDetails(id).enqueue(object : Callback<MoviesDetailsResponse> {
            override fun onResponse(call: Call<MoviesDetailsResponse>, response: Response<MoviesDetailsResponse>) {
                callback.onAllMoviesDetailsReceived(response.body())

                decrement()
            }

            override fun onFailure(call: Call<MoviesDetailsResponse>, throwable: Throwable) {
                Log.e(TAG, "Failure ${throwable.message}")

                decrement()
            }
        })
    }

    fun getTvShowsList(callback: LoadTvShowsCallback) {
        increment()

        TMDBClientGetService().getTvShowsList(1).enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(call: Call<TvShowsResponse>, response: Response<TvShowsResponse>) {
                callback.onAllTvShowsReceived(response.body()?.result)

                decrement()
            }

            override fun onFailure(call: Call<TvShowsResponse>, throwable: Throwable) {
                Log.e(TAG, "Failure ${throwable.message}")

                decrement()
            }
        })
    }

    fun getTvShowsDetails(callback: LoadTvShowsDetailsCallback, id: String) {
        increment()

        TMDBClientGetService().getTvShowsDetails(id).enqueue(object : Callback<TvShowsDetailsResponse> {
            override fun onResponse(call: Call<TvShowsDetailsResponse>, response: Response<TvShowsDetailsResponse>) {
                callback.onAllTvShowsDetailsReceived(response.body())

                decrement()
            }

            override fun onFailure(call: Call<TvShowsDetailsResponse>, throwable: Throwable) {
                Log.e(TAG, "Failure ${throwable.message}")

                decrement()
            }
        })
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(moviesList: List<MoviesRemote>?)
    }

    interface LoadMoviesDetailsCallback {
        fun onAllMoviesDetailsReceived(moviesDetails: MoviesDetailsResponse?)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShowsList: List<TvShowsRemote>?)
    }

    interface LoadTvShowsDetailsCallback {
        fun onAllTvShowsDetailsReceived(tvShowsDetails: TvShowsDetailsResponse?)
    }

}