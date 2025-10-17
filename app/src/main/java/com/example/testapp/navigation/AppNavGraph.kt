package com.example.testapp.navigation




import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature.ui.UserDetailScreen
import com.example.feature.ui.UserListScreen
import com.example.feature.ui.UserListViewModel


@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    //created object of shared view model and pass both of the compose
    val viewModel: UserListViewModel = hiltViewModel()

   NavHost(navController = navController, startDestination = "userList") {
        composable("userList") { UserListScreen(navController,viewModel) }
        composable("userDetail") { UserDetailScreen(viewModel) }
    }
}