package com.alkema.fityou.ui.today

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alkema.fityou.NavOptions
import com.alkema.fityou.data.db.ExerciseDao
import com.alkema.fityou.data.db.WorkoutDao
import com.alkema.fityou.domain.db.WorkoutWithEntries
import com.alkema.fityou.domain.db.WorkoutWithEntriesAndExercises
import com.alkema.fityou.domain.db.entities.Exercise
import com.alkema.fityou.domain.usecases.CreateNewWorkoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

data class Entry(val workoutEntry: WorkoutWithEntries, val exercise: Exercise)

@HiltViewModel
class TodayViewModel @Inject constructor(
    val workoutDao: WorkoutDao, val exerciseDao: ExerciseDao,
    val createNewWorkoutUseCase: CreateNewWorkoutUseCase
) :
    ViewModel() {
    val sdf = SimpleDateFormat("EEEE d MMMM")
    val d = Date()
    val dayOfWeek = sdf.format(d)
    private var _workouts = mutableStateListOf<WorkoutWithEntriesAndExercises>()
    val workouts: SnapshotStateList<WorkoutWithEntriesAndExercises> = _workouts
    fun getTodaysWorkout() {
        val now = Calendar.getInstance()
        now.set(Calendar.HOUR_OF_DAY, 0)
        now.clear(Calendar.MINUTE)
        now.clear(Calendar.SECOND)
        now.clear(Calendar.MILLISECOND)
        var entriesWithExercises: MutableList<Entry> = mutableListOf()
        viewModelScope.launch(Dispatchers.IO) {
            val result = workoutDao.getWorkoutWithEntriesAndExercises()
            _workouts.clear()
            _workouts.addAll(result.filter { it.entries.isNotEmpty() })
            Log.d("Entry", result.toString())
        }
    }

    fun addWorkout(navOptions: NavOptions) {
        viewModelScope.launch {
            val result = createNewWorkoutUseCase()
            navOptions.logExercise(result)
        }
    }
}