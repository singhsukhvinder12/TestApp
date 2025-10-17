package com.example.core

import com.example.core.model.User
import com.example.core.model.Result
import com.example.core.network.ApiService
import com.example.core.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class UserRepositoryTest {

    private val apiService: ApiService = mockk()
    private val repository = UserRepository(apiService)

    @Test
    fun `getUsers returns Success when api call is successful`() = runTest {
        val mockUsers = listOf(User(1,
            "John",
            "Collins LLC",
            "john123",
        "john@gmail.com",
            "Dehli",
            "000012","Dehli","India","0987654321","hfjhf"),
            User(1,
            "Rahul",
            "ICICi LLC",
            "rahul123",
            "rahul@gmail.com",
            "Gurgaon",
            "000012","Gurgaon","India","989898989","hjjh"))
        coEvery { apiService.getUsers() } returns mockUsers

        val result = repository.getUsers()

        when (result) {
            is Result.Success -> {
                assertEquals(2, result.data.size)
                assertEquals("John", result.data[0].name)
            }
            else -> throw AssertionError("Expected Success but got $result")
        }
    }

    @Test
    fun `getUsers returns Error when api call fails`() = runTest {
        val exception = Exception("Network failed")
        coEvery { apiService.getUsers() } throws exception

        val result = repository.getUsers()

        when (result) {
            is Result.Error -> {
                assertEquals("Network failed", result.message)
                assertEquals(exception, result.throwable)
            }
            else -> throw AssertionError("Expected Error but got $result")
        }
    }
}