package com.example.moviegalaxy.data.remote

import com.example.moviegalaxy.data.remote.response.ApiResponse
import com.example.moviegalaxy.data.remote.response.MovieCrewResponse
import com.example.moviegalaxy.data.remote.response.MovieDetailResponse
import com.example.moviegalaxy.data.remote.response.VideoResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor() {

    companion object {
        private const val LANGUAGE_ENG = "eng-ENG"
        private const val BASE_URL = "https://api.themoviedb.org"
        private val apikey = "6acceb55053f1e220dfd5566bcb378dc"
    }

    private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private var retrofitService = retrofit.create(ApiService::class.java)

    fun getPopular(): Single<ApiResponse> {
        return retrofitService.getPopular(apikey)
    }

    fun getDetailMovie(id: Int): Single<MovieDetailResponse> {
        return retrofitService.getMovieDetails(id, LANGUAGE_ENG, apikey)
    }

    fun getCrewForMovie(id: Int): Single<MovieCrewResponse> {
        return retrofitService.getCrewForMovie(id, apikey, LANGUAGE_ENG)
    }

    fun getVideo(id: Int): Single<VideoResponse> {
        return retrofitService.getVideo(id, apikey, LANGUAGE_ENG)
    }
}