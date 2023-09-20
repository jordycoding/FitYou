package com.alkema.fityou.ui.today

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alkema.fityou.LocalNavOptions
import java.text.DateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodayView(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<TodayViewModel>()
    val navOptions = LocalNavOptions.current

    LaunchedEffect(Unit) {
        viewModel.getTodaysWorkout()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navOptions.addWorkout() }) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .padding(15.dp)
        ) {
            Text(text = "Today", style = MaterialTheme.typography.headlineLarge)
            Text(
                text = viewModel.dayOfWeek,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            LazyColumn(Modifier.padding(top = 5.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(viewModel.workouts) { workout ->
                    workout.entries.groupBy { it.exercise.exerciseName }.forEach { entry ->
                        ElevatedCard(Modifier.fillMaxWidth()) {
                            Column(Modifier.padding(10.dp)) {
                                Text(entry.key, style = MaterialTheme.typography.titleLarge)
                                entry.value.forEach { set ->
                                    Text("${set.workoutEntry.reps} reps ${set.workoutEntry.weight} kgs")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}