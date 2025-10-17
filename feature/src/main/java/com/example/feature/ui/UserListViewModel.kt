package com.example.feature.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.User
import com.example.core.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.core.model.Result

import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _users = MutableStateFlow<Result<List<User>>>(Result.Loading)
    val users: StateFlow<Result<List<User>>> = _users

    var selectedUser by mutableStateOf<User?>(null)
        private set

    fun selectUser(user: User) {
        selectedUser = user
    }

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.value = Result.Loading
            _users.value = repository.getUsers()
        }
    }
}