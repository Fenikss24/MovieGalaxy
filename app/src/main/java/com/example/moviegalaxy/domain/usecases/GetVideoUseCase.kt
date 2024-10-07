package com.example.moviegalaxy.domain.usecases

import com.example.moviegalaxy.data.remote.response.VideoResponse
import com.example.moviegalaxy.domain.repository.MovieRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetVideoUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getVideo(id: Int): Single<VideoResponse> {
        return repository.getVideo(id)
    }
}