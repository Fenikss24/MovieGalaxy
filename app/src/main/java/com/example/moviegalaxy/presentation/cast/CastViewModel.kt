package com.example.moviegalaxy.presentation.cast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviegalaxy.domain.entities.MovieDetails
import com.example.moviegalaxy.domain.usecases.GetDetailMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CastViewModel @Inject constructor(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
) : ViewModel() {

    private var disposable: Disposable? = null

    private val _movieCastLiveData = MutableLiveData<MovieDetails>()
    val movieLiveData: LiveData<MovieDetails> = _movieCastLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()

    fun getMovieDetail(id: Int?) {
        id?.let {
            disposable = getDetailMovieUseCase.getDetailMovie(id)
                .subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ cast ->
                    _movieCastLiveData.value = cast
                },
                    {
                        _errorLiveData.value = it
                    })
        }
    }
}