package com.example.starwars.presentation.home

import android.app.appsearch.SearchResult
import androidx.lifecycle.*
import com.example.starwars.domain.entities.Movie
import com.example.starwars.domain.usecases.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
) : ViewModel() {
    private var filterText = MutableLiveData("")

    private val _listPopularMoviesLiveData = MutableLiveData<List<Movie>>()
    val listPopularMoviesLiveData: LiveData<List<Movie>> =
        MediatorLiveData<List<Movie>>().apply {
            addSource(_listPopularMoviesLiveData) { value = filterMovies() }
            addSource(filterText) { value = filterMovies() }
        }

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

    private fun filterMovies(): List<Movie> {
        return _listPopularMoviesLiveData.value?.filter {
            it.title.contains(filterText.value ?: "",
                true)
        } ?: emptyList()
    }

    fun filter(text: String) {
        filterText.value = text
    }

    override fun onCleared() {
        disposables.clear()
    }
}