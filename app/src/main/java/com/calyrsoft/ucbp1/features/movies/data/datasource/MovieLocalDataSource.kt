package com.calyrsoft.ucbp1.features.movies.data.datasource

import com.calyrsoft.ucbp1.features.movies.data.datasource.local.dao.MovieDao
import com.calyrsoft.ucbp1.features.movies.data.datasource.local.entity.MovieEntity

class MovieLocalDataSource(private val movieDao: MovieDao) {

    suspend fun saveMovies(movies: List<MovieEntity>) {
        movieDao.refreshMovies(movies)
    }
}