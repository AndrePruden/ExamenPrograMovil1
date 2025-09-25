package com.calyrsoft.ucbp1.features.movies.domain.usecase

import com.calyrsoft.ucbp1.features.movies.domain.model.MovieModel
import com.calyrsoft.ucbp1.features.movies.domain.repository.IMovieRepository

class GetPopularMoviesUseCase(
    private val repository: IMovieRepository
) {
    suspend fun invoke(): Result<List<MovieModel>> {
        return repository.getPopularMovies()
    }
}