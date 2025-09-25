package com.calyrsoft.ucbp1.features.dollar.data.repository

import com.calyrsoft.ucbp1.features.dollar.datasource.RealTimeRemoteDataSource
import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel
import com.calyrsoft.ucbp1.features.dollar.domain.repository.IDollarRepository
import kotlinx.coroutines.flow.Flow

class DollarRepository(
    private val realTimeRemoteDataSource: RealTimeRemoteDataSource
) : IDollarRepository {

    override suspend fun getDollar(): Flow<DollarModel> {
        return realTimeRemoteDataSource.getDollarUpdates()
    }
}