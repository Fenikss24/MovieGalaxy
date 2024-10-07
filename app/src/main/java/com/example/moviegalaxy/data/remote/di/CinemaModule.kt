package com.example.moviegalaxy.data.remote.di

import com.example.moviegalaxy.data.remote.RemoteDataSource
import com.example.moviegalaxy.data.remote.repository.MovieRepositoryImpl
import com.example.moviegalaxy.domain.repository.MovieRepository
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
