package com.rappi.movie.data.repository

import com.rappi.movie.domain.model.MovieModel
import com.rappi.movie.domain.mapper.convertToModel
import com.rappi.movie.data.datasource.MovieDatasourceRemote
import com.rappi.movie.domain.model.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepository @Inject constructor(private val remoteDataSource: MovieDatasourceRemote) {

    suspend fun getUpcomingMovies(): Flow<List<MovieModel>> =
        remoteDataSource.getUpcomingMovies().map { listUpcomingMovies -> listUpcomingMovies.convertToModel() }

    suspend fun getTrendingMovies(): Flow<List<MovieModel>> =
        remoteDataSource.getTrendingMovies().map { listTrendingMovie -> listTrendingMovie.convertToModel() }

    suspend fun getMovie(movieId: Int): Flow<List<Video>> =
        remoteDataSource.getMovies(movieId).map { video -> video.convertToModel() }
}