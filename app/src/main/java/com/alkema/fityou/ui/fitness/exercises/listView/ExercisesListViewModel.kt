package com.alkema.fityou.ui.fitness.exercises.listView

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import com.alkema.fityou.data.db.AppDatabase
import com.alkema.fityou.data.db.ExerciseDao
import com.alkema.fityou.domain.db.ExerciseWithMuscleGroups
import com.alkema.fityou.domain.db.entities.Exercise
import com.alkema.fityou.domain.db.entities.MuscleGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExercisesListViewModel @Inject constructor(private val exerciseDao: ExerciseDao) :
    ViewModel() {
        private val _exercisesList = mutableStateOf<List<ExerciseWithMuscleGroups>>(listOf())
    val exercisesList: State<List<ExerciseWithMuscleGroups>> = _exercisesList

    private val _filteredOut = mutableStateListOf<List<MuscleGroup>>()
    val filteredOut: SnapshotStateList<List<MuscleGroup>> = _filteredOut

    fun getExercisesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = exerciseDao.getExercisesWithMuscleGroups()
            val test = result.groupBy {it.muscleGroups}.map {it.key to it.value}
            _exercisesList.value = result
            Log.d("Kuthoerenaids", test.toString())
            Log.d("Grouped", result.groupBy { it.muscleGroups }.map { it.key to it.value }.toString())
        }
    }

    fun filterToggle(muscleGroups: List<MuscleGroup>) {
        if (_filteredOut.contains(muscleGroups)) {
            _filteredOut.remove(muscleGroups)
        } else {
            _filteredOut.add(muscleGroups)
        }
    }

    fun removeExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            exerciseDao.delete(exercise)
        }
    }
}