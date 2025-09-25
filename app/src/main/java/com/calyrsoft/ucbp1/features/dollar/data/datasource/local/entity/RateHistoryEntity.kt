package com.calyrsoft.ucbp1.features.dollar.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rate_history")
data class RateHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val usdtRate: String,
    val usdcRate: String,
    val timestamp: Long = System.currentTimeMillis()
)