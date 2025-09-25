package com.calyrsoft.ucbp1.features.dollar.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.calyrsoft.ucbp1.features.dollar.data.datasource.local.dao.RateHistoryDao
import com.calyrsoft.ucbp1.features.dollar.data.datasource.local.entity.RateHistoryEntity

@Database(entities = [RateHistoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rateHistoryDao(): RateHistoryDao
}