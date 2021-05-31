package com.dicoding.submissiondua.utils

import com.dicoding.submissiondua.data.entity.MoviesEntity
import com.dicoding.submissiondua.data.entity.TvShowsEntity
import com.dicoding.submissiondua.data.remote.response.movies.MoviesRemote
import com.dicoding.submissiondua.data.remote.response.tv_shows.TvShowsRemote

object DummyData {

    fun getMovies(): ArrayList<MoviesEntity> {
        ArrayList<MoviesEntity>().also {
            it.apply {
                add(
                    MoviesEntity(
                        578701,
                        "/xCEg6KowNISWvMh8GvPSxtdf9TO.jpg",
                        "Those Who Wish Me Dead",
                        7.1,
                        "2021-05-05"
                    )
                )
                add(
                    MoviesEntity(
                        460465,
                        "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                        "Mortal Kombat",
                        7.6,
                        "2021-04-07"
                    )
                )
                add(
                    MoviesEntity(
                        632357,
                        "/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
                        "The Unholy",
                        7.3,
                        "2021-03-31"
                    )
                )
            }

            return it
        }
    }

    fun getTvShows(): ArrayList<TvShowsEntity> {
        ArrayList<TvShowsEntity>().also {
            it.apply {
                add(
                    TvShowsEntity(
                        120168,
                        "/o7uk5ChRt3quPIv8PcvPfzyXdMw.jpg",
                        "Who Killed Sara?",
                        7.8,
                        "2021-03-24"
                    )
                )
                add(
                    TvShowsEntity(
                        60735,
                        "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                        "The Flash",
                        7.7,
                        "2014-10-07"
                    )
                )
                add(
                    TvShowsEntity(
                        71712,
                        "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                        "The Good Doctor",
                        8.6,
                        "2017-09-25"
                    )
                )
            }

            return it
        }
    }

    fun getMoviesRemote(): ArrayList<MoviesRemote> {
        ArrayList<MoviesRemote>().also {
            it.apply {
                add(
                    MoviesRemote(
                        id = 578701,
                        poster = "/xCEg6KowNISWvMh8GvPSxtdf9TO.jpg",
                        title = "Those Who Wish Me Dead",
                        rating = 7.1,
                        releaseDate = "2021-05-05"
                    )
                )
                add(
                    MoviesRemote(
                        id = 460465,
                        poster = "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                        title = "Mortal Kombat",
                        rating = 7.6,
                        releaseDate = "2021-04-07"
                    )
                )
                add(
                    MoviesRemote(
                        id = 632357,
                        poster = "/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
                        title = "The Unholy",
                        rating = 7.3,
                        releaseDate = "2021-03-31"
                    )
                )
            }

            return it
        }
    }

    fun getTvShowsRemote(): ArrayList<TvShowsRemote> {
        ArrayList<TvShowsRemote>().also {
            it.apply {
                add(
                    TvShowsRemote(
                        id = 120168,
                        poster = "/o7uk5ChRt3quPIv8PcvPfzyXdMw.jpg",
                        title = "Who Killed Sara?",
                        rating = 7.8,
                        releaseDate = "2021-03-24"
                    )
                )
                add(
                    TvShowsRemote(
                        id = 60735,
                        poster = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                        title = "The Flash",
                        rating = 7.7,
                        releaseDate = "2014-10-07"
                    )
                )
                add(
                    TvShowsRemote(
                        id = 71712,
                        poster = "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                        title = "The Good Doctor",
                        rating = 8.6,
                        releaseDate = "2017-09-25"
                    )
                )
            }

            return it
        }
    }

}