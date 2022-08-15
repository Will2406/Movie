package com.rappi.movie.data.datasource

import com.rappi.movie.data.api.MovieService
import com.rappi.movie.data.api.dto.TrendingMovie
import com.rappi.movie.data.api.dto.UpcomingMovie
import com.rappi.movie.data.api.dto.VideoResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDatasourceRemoteImpl @Inject constructor(private val api: MovieService) : MovieDatasourceRemote {

    override suspend fun getUpcomingMovies(): Flow<List<UpcomingMovie>> = flow {
        api.getUpcomingMovies(1).body()?.results?.let { emit(it) }
    }

    override suspend fun getTrendingMovies(): Flow<List<TrendingMovie>> = flow {
        api.getTrendingMovie().body()?.results?.let { emit(it) }
    }

    override suspend fun getMovies(movieId: Int): Flow<List<VideoResponse.VideoItemResponse>> = flow {
        api.getVideosMovie(movieId).body()?.results?.let { emit(it) }
    }

}