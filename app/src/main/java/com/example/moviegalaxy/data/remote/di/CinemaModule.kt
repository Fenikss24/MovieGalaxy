package com.example.moviegalaxy.data.remote.di

import com.example.moviegalaxy.data.remote.ApiService
import com.example.moviegalaxy.data.remote.repository.MovieRepositoryImpl
import com.example.moviegalaxy.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CinemaModule {

    @Singleton
    @Provides
    fun provideCinemaRepository(
        apiService: ApiService,
    ): MovieRepository {
        return MovieRepositoryImpl(apiService)
    }
}


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    private val BASE_URL = "https://api.themoviedb.org"
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
