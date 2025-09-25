package com.calyrsoft.ucbp1.features.login.data.repository

import com.calyrsoft.ucbp1.features.login.domain.model.LoginResult
import com.calyrsoft.ucbp1.features.login.domain.repository.ILoginRepository
import kotlinx.coroutines.delay

class LoginRepository : ILoginRepository {
    override suspend fun login(email: String, pass: String): LoginResult {
        delay(1500)

        return if (email == "andre.prudencio@ucb.edu.bo" && pass == "login") {
            LoginResult.Success
        } else {
            LoginResult.Failure.InvalidCredentials
        }
    }
}