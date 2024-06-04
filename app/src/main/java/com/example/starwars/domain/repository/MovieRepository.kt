package com.example.starwars.domain.repository

import com.example.starwars.domain.entities.Movie
import io.reactivex.rxjava3.core.Single

interface MovieRepository {

    fun getPopularMovieList(): Single<List<Movie>>
}