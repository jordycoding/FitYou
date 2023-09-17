package com.alkema.fityou.ui.fitness.muscles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusclesView(modifier: Modifier = Modifier) {
    var isDialogOpen by remember { mutableStateOf(false) }
    var muscleGroupName by remember { mutableStateOf("") }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { isDialogOpen = true }) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            AddMuscleDialog(
                showDialog = isDialogOpen,
                onDismissRequest = { isDialogOpen = false },
                onConfirmation = { muscleGroupName = it }
            )
            Column() {
                Text("test")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMuscleDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text("Add muscle group") },
            text = {
                OutlinedTextField(label = {Text("Name")}, value = text, onValueChange = { text = it })
            },
            confirmButton = {
                TextButton(onClick = { onConfirmation(text) }) {
                    Text("Add")
                }
            }, dismissButton = {
                TextButton(onClick = { onDismissRequest() }) {
                    Text("Dismiss")
                }
            })
    }
}