package com.example.moviegalaxy.domain.usecases

import com.example.moviegalaxy.domain.entities.MovieDetails
import com.example.moviegalaxy.domain.repository.MovieRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getDetailMovie(id: Int): Single<MovieDetails> {
        return repository.getDetailMovie(id)
    }
}