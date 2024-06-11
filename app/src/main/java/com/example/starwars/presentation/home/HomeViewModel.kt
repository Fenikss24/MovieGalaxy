package com.example.starwars.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwars.domain.entities.Movie
import com.example.starwars.domain.usecases.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
) : ViewModel() {

    private val _listPopularMoviesLiveData = MutableLiveData<List<Movie>>()
    val listPopularMoviesLiveData: LiveData<List<Movie>> = _listPopularMoviesLiveData

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> = _isLoadingLiveData

    private val disposables: CompositeDisposable = CompositeDisposable()

    init {
        refreshCinema()
    }

    fun refreshCinema() {
        _isLoadingLiveData.value = true
        getMovieListUseCase.getPopularMovieList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isLoadingLiveData.value = false
                _listPopularMoviesLiveData.value = it
            }, {
                _isLoadingLiveData.value = false
            }).also {
                disposables.add(it)
            }

    }

    override fun onCleared() {
        disposables.clear()
    }
}