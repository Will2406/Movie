package com.rappi.movie.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rappi.movie.data.safeLaunch
import com.rappi.movie.domain.usecase.GetTrendingMoviesUseCase
import com.rappi.movie.domain.usecase.GetUpcomingMoviesUseCase
import com.rappi.movie.domain.model.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getUpcomingMovies: GetUpcomingMoviesUseCase,
    private val getTrendingMovies: GetTrendingMoviesUseCase
) : ViewModel() {

    private val _upcomingMoviesList = MutableLiveData<List<MovieModel>>()
    val upcomingMoviesList: LiveData<List<MovieModel>> get() = _upcomingMoviesList

    private val _trendingMoviesList = MutableLiveData<List<MovieModel>>()
    val trendingMoviesList: LiveData<List<MovieModel>> get() = _trendingMoviesList

    var isInitialized = false

    fun loadHome() {

        if (!isInitialized) {
            initData()
            isInitialized = true
        }


    }

    fun initData() {
        getUpcomingMovies()
        getTrendingMovies()
    }

    private fun getUpcomingMovies() {
        viewModelScope.safeLaunch {
            getUpcomingMovies.invoke().catch { }.collect {
                _upcomingMoviesList.postValue(it)
            }
        }
    }

    private fun getTrendingMovies() {
        viewModelScope.safeLaunch {
            getTrendingMovies.invoke().catch { }.collect {
                _trendingMoviesList.postValue(it)
            }
        }
    }
}