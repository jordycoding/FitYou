package com.alkema.fityou.ui.fitness.exercises

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alkema.fityou.LocalNavOptions
import com.alkema.fityou.ui.fitness.exercises.listView.ExercisesListView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExercisesView(modifier: Modifier = Modifier) {
    val navOptions = LocalNavOptions.current
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { navOptions.addExercise() }) {
            Icon(Icons.Filled.Add, contentDescription = null)
        }
    }) { innerPadding ->
        ExercisesListView(Modifier.padding(innerPadding))
    }
}