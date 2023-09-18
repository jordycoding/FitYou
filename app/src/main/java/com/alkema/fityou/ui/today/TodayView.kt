package com.alkema.fityou.ui.today

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import java.text.DateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodayView(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<TodayViewModel>()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(10.dp)) {
            Text(text = "Today", fontSize = 28.sp)
            Text(text = viewModel.dayOfWeek, fontSize = 18.sp, fontWeight = FontWeight.Light)
        }
    }
}