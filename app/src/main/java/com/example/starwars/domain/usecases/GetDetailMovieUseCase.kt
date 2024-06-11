package com.example.starwars.domain.usecases

import com.example.starwars.domain.entities.MovieDetails
import com.example.starwars.domain.repository.MovieRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getDetailMovie(id: Int): Single<MovieDetails> {
        return repository.getDetailMovie(id)
    }
}