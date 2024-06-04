package com.example.starwars.data.remote.repository

import com.example.starwars.data.remote.RemoteDataSource
import com.example.starwars.domain.entities.Movie
import com.example.starwars.domain.repository.MovieRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource,
) : MovieRepository {

    override fun getPopularMovieList(): Single<List<Movie>> {
        return dataSource.getPopular().map { responseResult ->
            responseResult.results.map { movieFromApi ->
                Movie(
                    id = movieFromApi.id,
                    overview = movieFromApi.overview,
                    poster_path = movieFromApi.poster_path,
                    release_date = movieFromApi.release_date,
                    title = movieFromApi.title,
                    vote_average = movieFromApi.vote_average
                )
            }
        }
    }
}