package com.example.starwars.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwars.domain.entities.MovieDetails
import com.example.starwars.domain.usecases.GetDetailMovieUseCase
import com.example.starwars.domain.usecases.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
) : ViewModel() {

    private var disposable: Disposable? = null

    private val _movieLiveData = MutableLiveData<MovieDetails>()
    val movieLiveData: LiveData<MovieDetails> = _movieLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()

    fun getMovieDetail(id: Int?) {
        id?.let {
            disposable = getDetailMovieUseCase.getDetailMovie(id)
                .subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ movieDetails ->
                    _movieLiveData.value = movieDetails
                },
                    {
                        _errorLiveData.value = it
                    })
        }
    }
}