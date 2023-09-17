package com.alkema.fityou.ui.fitness.exercises.listView

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExercisesListView(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<ExercisesListViewModel>()
    LaunchedEffect(Unit) {
        viewModel.getExercisesList()
    }
    val exercisesList = viewModel.exercisesList.value
    LazyColumn {
        items(exercisesList) { exercise ->
            ListItem(headlineText = { Text(exercise.exerciseName) })
        }
    }
}