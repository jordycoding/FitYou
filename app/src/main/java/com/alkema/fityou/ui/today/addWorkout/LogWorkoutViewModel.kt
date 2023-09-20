package com.alkema.fityou.ui.today.addWorkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alkema.fityou.data.db.WorkoutDao
import com.alkema.fityou.domain.db.entities.Workout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogWorkoutViewModel @Inject constructor(private val workoutDao: WorkoutDao): ViewModel() {
    var workoutId: Long = -1
    fun createWorkout() {
        viewModelScope.launch(Dispatchers.IO) {
            val workout = Workout(0, System.currentTimeMillis())
            val result = workoutDao.insertAll(workout)
            workoutId = result[0]
        }
    }
}