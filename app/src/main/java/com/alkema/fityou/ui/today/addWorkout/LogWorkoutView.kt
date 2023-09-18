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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alkema.fityou.LocalNavOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogWorkoutView(modifier: Modifier = Modifier) {
    val navOptions = LocalNavOptions.current
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = ("Log workout")) },
                navigationIcon = {
                    IconButton(onClick = { navOptions.goBack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text("Save")
                    }
                })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navOptions.logExercise() }) {
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