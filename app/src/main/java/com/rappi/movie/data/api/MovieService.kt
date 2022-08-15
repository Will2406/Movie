package com.rappi.movie.data.api

import com.rappi.movie.data.api.dto.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int): Response<UpcomingMovieResponse>

    @GET("trending/movie/week")
    suspend fun getTrendingMovie(): Response<TrendingMovieResponse>

    @GET("movie/{movieId}/videos")
    suspend fun getVideosMovie(@Path("movieId") movieId: Int): Response<VideoResponse>

}