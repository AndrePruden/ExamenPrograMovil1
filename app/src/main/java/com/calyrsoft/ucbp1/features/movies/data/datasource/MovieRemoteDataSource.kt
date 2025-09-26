package com.calyrsoft.ucbp1.features.movies.data.datasource

import com.calyrsoft.ucbp1.features.github.data.error.DataException
import com.calyrsoft.ucbp1.features.movies.data.api.MovieService
import com.calyrsoft.ucbp1.features.movies.data.api.dto.MovieDto

class MovieRemoteDataSource(private val movieService: MovieService) {

    private val apiKey = "fa3e844ce31744388e07fa47c7c5d8c3"

    suspend fun getPopularMovies(): Result<List<MovieDto>> {
        try {
            val response = movieService.getPopularMovies(apiKey)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Result.success(body.results)
                } else {
                    return Result.failure(DataException.NoContent)
                }
            } else {
                return Result.failure(DataException.Unknown(response.message()))
            }
        } catch (e: Exception) {
            return Result.failure(DataException.Network)
        }
    }
}