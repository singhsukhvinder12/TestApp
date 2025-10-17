package com.example.feature

import com.example.feature.ui.UserListViewModel
import com.example.core.model.Result
import com.example.core.model.User
import com.example.core.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserListViewModelTest {

    private val repository: UserRepository = mockk()

    @Test
    fun `fetchUsers updates users state to Success`() = runTest {
        val mockUsers = listOf(User(1,
            "John",
            "Collins LLC",
            "john123",
            "john@gmail.com",
            "Dehli",
            "000012","Dehli","India","0987654321","hfjhf"))
        coEvery { repository.getUsers() } returns Result.Success(mockUsers)

        val viewModel = UserListViewModel(repository)

        val result = viewModel.users.first()

        // Assert the result is Success
        assert(result is Result.Success)
        if (result is Result.Success) {
            assertEquals(1, result.data.size)
            assertEquals("John", result.data[0].name)
        }
    }

    @Test
    fun `fetchUsers updates users state to Error`() = runTest {
        val errorMessage = "Network failed"
        coEvery { repository.getUsers() } returns Result.Error(errorMessage)

        val viewModel = UserListViewModel(repository)

        val result = viewModel.users.first()

        // Assert the result is Error
        assert(result is Result.Error)
        if (result is Result.Error) {
            assertEquals(errorMessage, result.message)
        }
    }

    @Test
    fun `selectUser updates selectedUser`() = runTest {
        val viewModel = UserListViewModel(mockk())
        val user = User(1,
            "John",
            "Collins LLC",
            "john123",
            "john@gmail.com",
            "Dehli",
            "000012","Dehli","India","0987654321","hfjhf")

        assertNull(viewModel.selectedUser)

        viewModel.selectUser(user)
        assertEquals(user, viewModel.selectedUser)
    }
}