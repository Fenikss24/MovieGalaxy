package com.example.moviegalaxy.domain.repository

import com.example.moviegalaxy.data.remote.response.VideoResponse
import com.example.moviegalaxy.domain.entities.Movie
import com.example.moviegalaxy.domain.entities.MovieDetails
import io.reactivex.rxjava3.core.Single

interface MovieRepository {

    fun getPopularMovieList(): Single<List<Movie>>

    fun getDetailMovie(id: Int): Single<MovieDetails>

    fun getVideo(id: Int): Single<VideoResponse>

}