package com.example.starwars.data.remote.di

import com.example.starwars.data.remote.RemoteDataSource
import com.example.starwars.data.remote.repository.MovieRepositoryImpl
import com.example.starwars.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CinemaModule {

    @Singleton
    @Provides
    fun provideCinemaRepository(
        dataSource: RemoteDataSource,
    ): MovieRepository {
        return MovieRepositoryImpl(dataSource)
    }
}
