package com.rappi.movie.ui.movie

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

    private val _upcomingMoviesList = MutableSharedFlow<List<MovieModel>>()
    val upcomingMoviesList get() = _upcomingMoviesList.asSharedFlow()

    private val _trendingMoviesList = MutableSharedFlow<List<MovieModel>>()
    val trendingMoviesList get() = _trendingMoviesList.asSharedFlow()


    fun initData() {
        getUpcomingMovies()
        getTrendingMovies()
    }

    private fun getUpcomingMovies() {
        viewModelScope.safeLaunch {
            getUpcomingMovies.invoke().catch { }.collect {
                _upcomingMoviesList.emit(it)
            }
        }
    }

    private fun getTrendingMovies() {
        viewModelScope.safeLaunch {
            getTrendingMovies.invoke().catch { }.collect {
                _trendingMoviesList.emit(it)
            }
        }
    }
}