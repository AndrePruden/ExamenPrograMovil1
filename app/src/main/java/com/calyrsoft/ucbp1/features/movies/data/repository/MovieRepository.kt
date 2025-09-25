package com.calyrsoft.ucbp1.features.movies.data.repository

import com.calyrsoft.ucbp1.features.github.data.error.DataException
import com.calyrsoft.ucbp1.features.github.domain.error.Failure
import com.calyrsoft.ucbp1.features.movies.data.datasource.MovieRemoteDataSource
import com.calyrsoft.ucbp1.features.movies.domain.model.MovieModel
import com.calyrsoft.ucbp1.features.movies.domain.repository.IMovieRepository

class MovieRepository(
    private val remoteDataSource: MovieRemoteDataSource
) : IMovieRepository {
    private val imageBaseUrl = "https://image.tmdb.org/t/p/w185"

    override suspend fun getPopularMovies(): Result<List<MovieModel>> {
        val response = remoteDataSource.getPopularMovies()

        return response.fold(
            onSuccess = { moviesDto ->
                val movies = moviesDto.mapNotNull { dto ->
                    dto.posterPath?.let {
                        MovieModel(
                            id = dto.id,
                            title = dto.title,
                            posterUrl = "$imageBaseUrl${dto.posterPath}"
                        )
                    }
                }
                Result.success(movies)
            },
            onFailure = { exception ->
                val failure = when (exception) {
                    is DataException.Network -> Failure.NetworkConnection
                    is DataException.NoContent -> Failure.EmptyBody
                    else -> Failure.Unknown(exception)
                }
                Result.failure(failure)
            }
        )
    }
}