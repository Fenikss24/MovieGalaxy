package com.example.starwars.domain.usecases

import com.example.starwars.data.remote.response.VideoResponse
import com.example.starwars.domain.repository.MovieRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetVideoUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getVideo(id: Int): Single<VideoResponse> {
        return repository.getVideo(id)
    }
}