package com.calyrsoft.ucbp1.features.dollar.data.datasource

import com.calyrsoft.ucbp1.features.dollar.data.datasource.local.dao.RateHistoryDao
import com.calyrsoft.ucbp1.features.dollar.data.datasource.local.entity.RateHistoryEntity
import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel

class DollarLocalDataSource(private val rateHistoryDao: RateHistoryDao) {
    suspend fun saveRates(dollarModel: DollarModel) {
        val usdt = dollarModel.dollarUsdt
        val usdc = dollarModel.dollarUsdc

        if (usdt != null && usdc != null) {
            val historyEntry = RateHistoryEntity(
                usdtRate = usdt,
                usdcRate = usdc
            )
            rateHistoryDao.insert(historyEntry)
        }
    }
}