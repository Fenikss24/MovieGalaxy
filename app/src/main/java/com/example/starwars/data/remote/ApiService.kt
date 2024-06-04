package com.example.starwars.data.remote

import com.example.starwars.data.remote.response.ApiResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        private const val QUERY_LANGUAGE = "language"
        private const val QUERY_API_KEY = "api_key"
        private const val QUERY_MOVIE_ID = "movie_id"
        private const val LANGUAGE_ENG = "eng-ENG"
        private const val PAGE = "page"
        private const val QUERY_PARAM_LIST_OF_GENRE = "with_genres"
    }

    @GET("3/movie/popular")
    fun getPopular(
        @Query(QUERY_API_KEY) api_key: String,
        @Query(QUERY_LANGUAGE) language: String? = LANGUAGE_ENG
    ): Single<ApiResponse>
}