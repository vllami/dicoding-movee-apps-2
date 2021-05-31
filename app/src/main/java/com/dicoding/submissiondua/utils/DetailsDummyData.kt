package com.dicoding.submissiondua.utils

import com.dicoding.submissiondua.data.entity.DetailsEntity
import com.dicoding.submissiondua.data.remote.response.GenreResponse
import com.dicoding.submissiondua.data.remote.response.movies.MoviesDetailsResponse
import com.dicoding.submissiondua.data.remote.response.tv_shows.TvShowsDetailsResponse

object DetailsDummyData {

    fun getMoviesDetails(): DetailsEntity {
        return DetailsEntity(
            567189,
            "/fPGeS6jgdLovQAKunNHX8l0avCy.jpg",
            "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
            "Tom Clancy's Without Remorse",
            7.3,
            "2021-04-29",
            listOf("Action", "Adventure", "Thriller", "War"),
            "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife."
        )
    }

    fun getTvShowsDetails(): DetailsEntity {
        return DetailsEntity(
            88396,
            "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
            "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            "The Falcon and the Winter Soldier",
            7.9,
            "2021-03-19",
            listOf("Sci-Fi & Fantasy", "Action & Adventure", "Drama", "War & Politics"),
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience."
        )
    }

    fun getMoviesDetailsRemote(): MoviesDetailsResponse {
        return MoviesDetailsResponse(
            id = 567189,
            backdrop = "/fPGeS6jgdLovQAKunNHX8l0avCy.jpg",
            poster = "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
            title = "Tom Clancy's Without Remorse",
            releaseDate = "2021-04-29",
            genre = listOf(
                GenreResponse(
                    id = 28,
                    name = "Action"
                ),
                GenreResponse(
                    id = 12,
                    name = "Adventure"
                ),
                GenreResponse(
                    id = 53,
                    name = "Thriller"
                ),
                GenreResponse(
                    id = 10752,
                    name = "War"
                )
            ),
            synopsis = "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
            rating = 7.3
        )
    }

    fun getTvShowsDetailsRemote(): TvShowsDetailsResponse {
        return TvShowsDetailsResponse(
            id = 88396,
            backdrop = "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
            poster = "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            title = "The Falcon and the Winter Soldier",
            releaseDate = "2021-03-19",
            genre = listOf(
                GenreResponse(
                    id = 10765,
                    name = "Sci-Fi & Fantasy"
                ),
                GenreResponse(
                    id = 10759,
                    name = "Action & Adventure"
                ),
                GenreResponse(
                    id = 18,
                    name = "Drama"
                ),
                GenreResponse(
                    id = 10768,
                    name = "War & Politics"
                )
            ),
            synopsis = "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            rating = 7.9
        )
    }

}