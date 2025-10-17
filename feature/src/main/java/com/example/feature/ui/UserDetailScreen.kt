package com.example.feature.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserDetailScreen(viewModel: UserListViewModel) {
   //just collecting the data from shared view model
    viewModel.selectedUser?.let {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .wrapContentHeight().padding(top = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE0F7FA)
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth().padding(top = 10.dp),
                    contentAlignment = Alignment.TopCenter
                ){
                    Text(text = "User Details", fontSize = 25.sp)
                }

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(text = "Name: ${it.name}", fontSize = 18.sp)
                    Text(text = "Email: ${it.email}", fontSize = 16.sp)
                    Text(text = "Phone: ${it.phone}", fontSize = 16.sp)
                    Text(text = "Company: ${it.company}", fontSize = 16.sp)
                    Text(text = "State: ${it.state}", fontSize = 16.sp)
                    Text(text = "Address: ${it.address}", fontSize = 16.sp)
                }
            }
        }
    }
}