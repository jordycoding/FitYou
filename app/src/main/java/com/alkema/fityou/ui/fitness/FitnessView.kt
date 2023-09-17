package com.alkema.fityou.ui.fitness

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.alkema.fityou.ui.fitness.exercises.ExercisesView
import com.alkema.fityou.ui.fitness.muscles.MusclesView
import kotlinx.coroutines.launch

sealed class TabItem(val title: String, val screen: @Composable () -> Unit) {
    object Exercises : TabItem("Exercises", { ExercisesView() })
    object MuscleGroups : TabItem("Muscle groups", { MusclesView() })
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FitnessView(modifier: Modifier = Modifier) {
    val tabs = listOf(TabItem.Exercises, TabItem.MuscleGroups)
    var tabIndex by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    Column {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, item ->
                Tab(
                    selected = index == tabIndex,
                    text = { Text(item.title) },
                    onClick = { coroutineScope.launch { tabIndex = index } })
            }
        }
        tabs[tabIndex].screen()
    }
}