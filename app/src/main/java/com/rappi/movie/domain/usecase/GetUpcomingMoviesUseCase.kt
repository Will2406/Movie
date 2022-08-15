package com.rappi.movie.domain.usecase

import com.rappi.movie.domain.model.MovieModel
import com.rappi.movie.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(): Flow<List<MovieModel>> = repository.getUpcomingMovies()
}