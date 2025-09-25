package com.calyrsoft.ucbp1.features.login.domain.repository

import com.calyrsoft.ucbp1.features.login.domain.model.LoginResult

interface ILoginRepository {
    suspend fun login(email: String, pass: String): LoginResult
}