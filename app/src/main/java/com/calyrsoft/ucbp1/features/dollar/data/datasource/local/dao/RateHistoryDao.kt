package com.calyrsoft.ucbp1.features.dollar.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.calyrsoft.ucbp1.features.dollar.data.datasource.local.entity.RateHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RateHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rate: RateHistoryEntity)

    @Query("SELECT * FROM rate_history ORDER BY timestamp DESC")
    fun getHistory(): Flow<List<RateHistoryEntity>>
}