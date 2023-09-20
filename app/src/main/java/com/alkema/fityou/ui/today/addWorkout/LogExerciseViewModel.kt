package com.alkema.fityou.ui.today.addWorkout

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alkema.fityou.data.db.ExerciseDao
import com.alkema.fityou.data.db.WorkoutEntryDao
import com.alkema.fityou.domain.db.ExerciseWithMuscleGroups
import com.alkema.fityou.domain.db.entities.WorkoutEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogExerciseViewModel @Inject constructor(
    private val exerciseDao: ExerciseDao,
    private val workoutEntryDao: WorkoutEntryDao
) : ViewModel() {
    private val _exercisesList = mutableStateOf<List<ExerciseWithMuscleGroups>>(listOf())
    val exercisesList: State<List<ExerciseWithMuscleGroups>> = _exercisesList
    private val _entryList = mutableStateListOf<ExerciseEntry>()
    val entryList: SnapshotStateList<ExerciseEntry> = _entryList

    fun getExercisesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = exerciseDao.getExercisesWithMuscleGroups()
            _exercisesList.value = result
        }
    }

    fun addEntry(weight: Float, reps: Int) {
        entryList.add(ExerciseEntry(weight, reps))
    }

    fun save(exerciseId: Long, workoutId: Long) {
        entryList.forEach { entry ->
            viewModelScope.launch(Dispatchers.IO) {
                val newEntry = WorkoutEntry(0, exerciseId, workoutId, entry.reps, entry.weight)
                workoutEntryDao.insertAll(newEntry)
            }
        }
    }
}