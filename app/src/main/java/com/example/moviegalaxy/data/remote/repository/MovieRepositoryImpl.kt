package com.example.moviegalaxy.data.remote.repository

import com.example.moviegalaxy.data.remote.RemoteDataSource
import com.example.moviegalaxy.data.remote.response.VideoResponse
import com.example.moviegalaxy.domain.entities.Cast
import com.example.moviegalaxy.domain.entities.Movie
import com.example.moviegalaxy.domain.entities.MovieDetails
import com.example.moviegalaxy.domain.repository.MovieRepository
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

    override fun getDetailMovie(id: Int): Single<MovieDetails> {
        val castsSingle = dataSource.getCrewForMovie(id)
        val movieDetailsSingle = dataSource.getDetailMovie(id)

        return Single.zip(movieDetailsSingle, castsSingle) { responseResult, casts ->
            MovieDetails(
                id = responseResult.id,
                title = responseResult.title,
                poster_path = responseResult.poster_path,
                casts = casts.castFromApi.map { castFromApi ->
                    Cast(
                        id = castFromApi.id,
                        name = castFromApi.name,
                        profile_path = castFromApi.profile_path,
                        original_name = castFromApi.original_name
                    )
                },
                release_date = responseResult.release_date,
                overview = responseResult.overview,
                runtime = responseResult.runtime,
                original_title = responseResult.original_title,
                vote_average = responseResult.vote_average
            )
        }

    }

    override fun getVideo(id: Int): Single<VideoResponse> {
        return dataSource.getVideo(id)
    }
}