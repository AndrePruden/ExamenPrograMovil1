package com.calyrsoft.ucbp1.features.movies.data.api

import com.calyrsoft.ucbp1.features.movies.data.api.dto.MovieResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MovieResponseDto>
}