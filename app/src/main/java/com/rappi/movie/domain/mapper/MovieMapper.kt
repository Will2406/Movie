package com.rappi.movie.domain.mapper

import com.rappi.movie.data.api.dto.TrendingMovie
import com.rappi.movie.data.api.dto.UpcomingMovie
import com.rappi.movie.domain.model.MovieModel

fun UpcomingMovie.convertToModel() = MovieModel(
    adult = adult,
    backdropPath = backdropPath,
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage.toString(),
    originalLanguage = originalLanguage
)

fun TrendingMovie.convertToModel() = MovieModel(
    adult = adult,
    backdropPath = backdropPath,
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage.toString(),
    originalLanguage = originalLanguage
)

@JvmName("convertToUpcomingMovies")
fun List<UpcomingMovie>.convertToModel() = map(UpcomingMovie::convertToModel)

@JvmName("convertToTrendingMovies")
fun List<TrendingMovie>.convertToModel() = map(TrendingMovie::convertToModel)