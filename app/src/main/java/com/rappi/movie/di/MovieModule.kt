package com.rappi.movie.di

import com.rappi.movie.data.api.ApiConfig
import com.rappi.movie.data.datasource.MovieDatasourceRemote
import com.rappi.movie.data.datasource.MovieDatasourceRemoteImpl
import com.rappi.movie.data.repository.MovieRepository
import com.rappi.movie.domain.usecase.GetTrendingMoviesUseCase
import com.rappi.movie.domain.usecase.GetUpcomingMoviesUseCase
import com.rappi.movie.domain.usecase.GetVideosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MovieModule {

    @Provides
    fun createDataSource(): MovieDatasourceRemote = MovieDatasourceRemoteImpl(ApiConfig.createInstance())

    @Provides
    fun createRepository(remote: MovieDatasourceRemote) = MovieRepository(remote)

    @Provides
    fun createUpComingUseCase(repository: MovieRepository) = GetUpcomingMoviesUseCase(repository)

    @Provides
    fun createTrendingUseCase(repository: MovieRepository) = GetTrendingMoviesUseCase(repository)

    @Provides
    fun createVideoUseCase(repository: MovieRepository) = GetVideosUseCase(repository)
}
