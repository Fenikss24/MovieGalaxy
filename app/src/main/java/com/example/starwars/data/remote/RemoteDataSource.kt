package com.example.starwars.data.remote

import com.example.starwars.BuildConfig
import com.example.starwars.data.remote.response.ApiResponse
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
}