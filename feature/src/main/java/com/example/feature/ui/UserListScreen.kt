package com.example.feature.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.core.model.Result
import com.example.core.model.User

@Composable
fun UserListScreen(navController: NavController, viewModel: UserListViewModel) {
    val usersState by viewModel.users.collectAsState()

    when (usersState) {
        is Result.Loading -> {
            Box(  modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.Center){
                Text("Loading...", fontSize = 22.sp)
            }

        }
        is Result.Success -> {

            val users = (usersState as Result.Success<List<User>>).data

            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                items(users) { user ->

                    Card(modifier = Modifier.fillMaxWidth().padding(8.dp).clickable {

                     // i have shared the clickable user details to view model
                        viewModel.selectUser(user)
                        navController.navigate("userDetail")
                    },
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFE0F7FA)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)) {

                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = user.name, style = MaterialTheme.typography.titleMedium)
                            Text(text = user.company, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
        is Result.Error -> {
            Text("Error: ${(usersState as Result.Error).message}")
        }
    }
}