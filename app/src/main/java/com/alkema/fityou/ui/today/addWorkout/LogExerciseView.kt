package com.alkema.fityou.ui.today.addWorkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alkema.fityou.LocalNavOptions
import com.alkema.fityou.domain.db.ExerciseWithMuscleGroups
import com.alkema.fityou.domain.db.entities.Exercise


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogExerciseView(modifier: Modifier = Modifier, workoutId: Long) {
    val navOptions = LocalNavOptions.current
    val viewModel = hiltViewModel<LogExerciseViewModel>()
    var isExpanded by remember { mutableStateOf(false) }
    var selectedExercise by remember { mutableStateOf<ExerciseWithMuscleGroups?>(null) }
    var weight by remember { mutableStateOf<Float>(0.0f) }
    var reps by remember { mutableStateOf<Int>(0) }

    LaunchedEffect(Unit) {
        viewModel.getExercisesList()
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Log exercise") }, actions = {
            TextButton(onClick = {
                selectedExercise?.exercise?.exerciseId?.let {
                    viewModel.save(it, workoutId)
                }
            }) {
                Text(text = "Save")
            }
        }, navigationIcon = {
            IconButton(onClick = { navOptions.goBack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        })
    }) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { if (viewModel.entryList.size == 0) isExpanded = it },
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = selectedExercise?.exercise?.exerciseName ?: "",
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    placeholder = { Text("Select an exercise") },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }) {
                    viewModel.exercisesList.value.groupBy { it.muscleGroups }.forEach {
                        it.value.forEach { exercise ->
                            DropdownMenuItem(
                                modifier = Modifier.fillMaxWidth(),
                                text = { Text(exercise.exercise.exerciseName) },
                                onClick = {
                                    selectedExercise = exercise
                                    isExpanded = false
                                })
                        }
                        Divider()
                    }
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { weight -= 2.5f }) {
                    Icon(Icons.Filled.Remove, contentDescription = null)
                }
                OutlinedTextField(
                    value = weight.toString(),
                    onValueChange = { weight = it.toFloat() },
                    label = { Text("Weight") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                IconButton(onClick = { weight += 2.5f }) {
                    Icon(Icons.Filled.Add, contentDescription = null)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { if (reps > 0) reps -= 1 }) {
                    Icon(Icons.Filled.Remove, contentDescription = null)
                }
                OutlinedTextField(
                    value = reps.toString(), onValueChange = { reps = it.toInt() },
                    label = { Text("Reps") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                IconButton(onClick = { reps += 1 }) {
                    Icon(Icons.Filled.Add, contentDescription = null)
                }

            }
            Button(
                onClick = { viewModel.addEntry(weight, reps) },
                Modifier.fillMaxWidth(),
                enabled = selectedExercise != null
            ) {
                Text("Add")
            }
            LazyColumn {
                itemsIndexed(viewModel.entryList) { index, entry ->
                    Row(Modifier.padding(5.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "${index + 1}")
                        Text("${entry.weight} kgs")
                        Text("${entry.reps} reps")
                    }
                    Divider()
                }
            }
        }
    }
}