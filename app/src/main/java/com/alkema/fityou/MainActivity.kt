package com.alkema.fityou

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alkema.fityou.ui.fitness.FitnessView
import com.alkema.fityou.ui.fitness.exercises.AddExerciseView
import com.alkema.fityou.ui.theme.FitYouTheme
import com.alkema.fityou.ui.today.TodayView
import com.alkema.fityou.ui.today.addWorkout.LogExerciseView
import com.alkema.fityou.ui.today.addWorkout.LogWorkoutView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitYouTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CompositionLocalProvider(LocalNavOptions provides NavOptions(navController)) {
                        NavHost(navController = navController, startDestination = "main") {
                            composable("main") { MainView(parentNavController = navController) }
                            composable("addExercise") { AddExerciseView() }
                            composable("logWorkoutView") { LogWorkoutView() }
                            composable(
                                "logExercise/{workoutId}",
                                arguments = listOf(navArgument("workoutId") {
                                    type = NavType.LongType
                                })
                            ) { backStackEntry -> LogExerciseView(workoutId = backStackEntry.arguments?.getLong("workoutId") ?: -1) }
                        }
                    }
                }
            }
        }
    }
}

class NavOptions constructor(private val navController: NavController) {
    fun addExercise() {
        navController.navigate("addExercise")
    }

    fun addWorkout() {
        navController.navigate("logWorkoutView")
    }

    fun logExercise(workoutId: Long) {
        navController.navigate("logExercise/${workoutId}")
    }

    fun goBack() {
        navController.popBackStack()
    }
}

val LocalNavOptions = compositionLocalOf<NavOptions> { error("No nav options") }

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
    val activeIcon: ImageVector
) {
    object Home : Screen("home", R.string.home, Icons.Outlined.Home, Icons.Filled.Home)
    object Calendar : Screen(
        "calendar",
        R.string.calendar,
        Icons.Outlined.CalendarMonth,
        Icons.Filled.CalendarMonth
    )

    object Exercises : Screen(
        "exercises",
        R.string.exercises,
        Icons.Outlined.FitnessCenter,
        Icons.Filled.FitnessCenter
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(modifier: Modifier = Modifier, parentNavController: NavController) {
    val items = listOf(Screen.Home, Screen.Calendar, Screen.Exercises)
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    val active =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    val icon = if (active) screen.activeIcon else screen.icon
                    NavigationBarItem(
                        icon = { Icon((icon), contentDescription = null) },
                        label = { Text(text = stringResource(id = screen.resourceId)) },
                        selected = active,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Home.route,
            modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { TodayView() }
            composable(Screen.Calendar.route) { Text("calendar") }
            composable(Screen.Exercises.route) {
                FitnessView()
            }
        }
    }
}