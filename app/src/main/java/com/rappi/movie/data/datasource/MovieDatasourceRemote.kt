package com.rappi.movie.data.datasource

import com.rappi.movie.data.api.dto.TrendingMovie
import com.rappi.movie.data.api.dto.UpcomingMovie
import com.rappi.movie.data.api.dto.VideoResponse
import kotlinx.coroutines.flow.Flow

interface MovieDatasourceRemote {

    suspend fun getUpcomingMovies(): Flow<List<UpcomingMovie>>
    suspend fun getTrendingMovies(): Flow<List<TrendingMovie>>
    suspend fun getMovies(movieId: Int): Flow<List<VideoResponse.VideoItemResponse>>
}