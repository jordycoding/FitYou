package com.alkema.fityou.ui.fitness.exercises.listView

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExercisesListView(
    modifier: Modifier = Modifier,
    selectCallback: () -> Unit = {},
    showDelete: Boolean = true
) {
    val viewModel = hiltViewModel<ExercisesListViewModel>()
    LaunchedEffect(Unit) {
        viewModel.getExercisesList()
    }
    val exercisesList = viewModel.exercisesList.value
    LazyColumn {
        items(exercisesList
            .groupBy { it.muscleGroups }.map { it.key to it.value }) { (muscleGroup, exercises) ->
            ListItem(headlineText = { Text(muscleGroup.joinToString(separator = "/") { it.muscleGroupName }, modifier = Modifier.clickable {
                viewModel.filterToggle(muscleGroup)
            }) })
            Divider()
            exercises.filter{!viewModel.filteredOut.contains(it.muscleGroups)}.forEach { exercise ->
                ListItem(
                    headlineText = { Text(exercise.exercise.exerciseName) },
                    trailingContent = {
                        if (showDelete) IconButton(onClick = { viewModel.removeExercise(exercise.exercise) }) {
                            Icon(Icons.Filled.Delete, contentDescription = null)
                        }
                    })
            }
        }
    }
}