package com.alkema.fityou.ui.today.addWorkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alkema.fityou.LocalNavOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogWorkoutView(modifier: Modifier = Modifier) {
    val navOptions = LocalNavOptions.current
    val viewModel = hiltViewModel<LogWorkoutViewModel>()

    LaunchedEffect(Unit) {
        viewModel.createWorkout()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = ("Log workout")) },
                actions = {
                    TextButton(onClick = { navOptions.goBack() }) {
                        Text("Done")
                    }
                })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navOptions.logExercise(viewModel.workoutId) }) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(10.dp)
        ) {
            Text("Adding workout")
        }
    }
}