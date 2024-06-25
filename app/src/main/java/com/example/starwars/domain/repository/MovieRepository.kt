package com.example.starwars.domain.repository

import com.example.starwars.data.remote.response.VideoResponse
import com.example.starwars.domain.entities.Movie
import com.example.starwars.domain.entities.MovieDetails
import io.reactivex.rxjava3.core.Single

interface MovieRepository {

    fun getPopularMovieList(): Single<List<Movie>>

    fun getDetailMovie(id: Int): Single<MovieDetails>

    fun getVideo(id: Int): Single<VideoResponse>

}