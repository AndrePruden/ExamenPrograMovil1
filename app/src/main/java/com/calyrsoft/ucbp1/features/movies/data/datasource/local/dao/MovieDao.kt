package com.calyrsoft.ucbp1.features.movies.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.calyrsoft.ucbp1.features.movies.data.datasource.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

    @Transaction
    suspend fun refreshMovies(movies: List<MovieEntity>) {
        deleteAllMovies()
        insertOrUpdateMovies(movies)
    }
}