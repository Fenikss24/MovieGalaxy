package com.example.starwars.domain.usecases

import com.example.starwars.domain.entities.Movie
import com.example.starwars.domain.repository.MovieRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getPopularMovieList(): Single<List<Movie>> {
        return repository.getPopularMovieList()
    }
}