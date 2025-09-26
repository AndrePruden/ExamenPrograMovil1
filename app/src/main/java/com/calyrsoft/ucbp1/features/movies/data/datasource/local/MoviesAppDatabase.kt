package com.calyrsoft.ucbp1.features.movies.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.calyrsoft.ucbp1.features.movies.data.datasource.local.dao.MovieDao
import com.calyrsoft.ucbp1.features.movies.data.datasource.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MoviesAppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}