package com.example.core.repository

import com.example.core.network.ApiService
import com.example.core.model.User
import com.example.core.model.Result
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getUsers(): Result<List<User>> {
        return try {
            val users = apiService.getUsers()
            Result.Success(users)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage ?: "Unknown error", e)
        }
    }
}