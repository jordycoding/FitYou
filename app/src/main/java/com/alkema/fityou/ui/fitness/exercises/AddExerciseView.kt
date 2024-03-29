package com.alkema.fityou.ui.fitness.exercises

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alkema.fityou.LocalNavOptions
import com.alkema.fityou.domain.db.entities.MuscleGroup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExerciseView(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<AddExerciseViewModel>()
    val navOptions = LocalNavOptions.current
    var exerciseName by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var muscleGroup by remember { mutableStateOf<MuscleGroup?>(null) }
    val muscleGroups = viewModel.muscleGroups.value
    val selectedGroups = remember { mutableStateListOf<MuscleGroup>() }

    LaunchedEffect(Unit) {
        viewModel.getMuscleGroups()
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Add new exercise") }, navigationIcon = {
            IconButton(onClick = { navOptions.goBack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        }, actions = {
            TextButton(onClick = {
                viewModel.saveExercise(exerciseName, selectedGroups, { navOptions.goBack() })
            }) {
                Text("Save")
            }
        })
    }) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(horizontal = 10.dp)
                .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                label = { Text(text = "Exercise name") },
                value = exerciseName,
                onValueChange = { exerciseName = it },
                modifier = Modifier.fillMaxWidth()
            )
            Column {
                Text(text = "Muscle groups", fontSize = 20.sp)
                Row() {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = it }) {
                        TextField(
                            value = muscleGroup?.muscleGroupName ?: "",
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
                            placeholder = { Text("Select a muscle group") },
                            colors = ExposedDropdownMenuDefaults.textFieldColors(),
                            modifier = Modifier.menuAnchor()
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }) {
                            muscleGroups.forEach { group ->
                                DropdownMenuItem(
                                    text = { Text(group.muscleGroupName) },
                                    onClick = {
                                        muscleGroup = group
                                        expanded = false
                                    })
                            }
                        }
                    }
                    TextButton(onClick = {
                        muscleGroup?.let { if (!selectedGroups.contains(it)) selectedGroups.add(it) }
                    }) {
                        Text("Add")
                    }
                }
                LazyColumn {
                    items(selectedGroups) { group ->
                        ListItem(headlineText = { Text(group.muscleGroupName) }, trailingContent = {
                            IconButton(onClick = { selectedGroups.remove(group) }) {
                                Icon(Icons.Filled.Delete, contentDescription = null)
                            }
                        })
                    }
                }
            }
        }
    }
}