package com.rappi.movie.domain.usecase

import com.rappi.movie.data.repository.MovieRepository
import com.rappi.movie.domain.model.Video
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(movieId: Int): Flow<List<Video>> = repository.getMovie(movieId)
}