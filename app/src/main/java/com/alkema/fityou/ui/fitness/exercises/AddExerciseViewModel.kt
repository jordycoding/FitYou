package com.alkema.fityou.ui.fitness.exercises

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alkema.fityou.data.db.ExerciseDao
import com.alkema.fityou.data.db.ExerciseMuscleGroupDao
import com.alkema.fityou.data.db.MuscleGroupDao
import com.alkema.fityou.domain.db.entities.Exercise
import com.alkema.fityou.domain.db.entities.MuscleGroup
import com.alkema.fityou.domain.db.entities.MuscleGroupExerciseCrossRef
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(
    private val muscleGroupDao: MuscleGroupDao,
    private val exerciseDao: ExerciseDao,
    private val exerciseMuscleGroupDao: ExerciseMuscleGroupDao
) : ViewModel() {
    private val _muscleGroups = mutableStateOf<List<MuscleGroup>>(listOf())
    val muscleGroups: State<List<MuscleGroup>> = _muscleGroups

    fun getMuscleGroups() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = muscleGroupDao.getAll()
            _muscleGroups.value = result
        }
    }

    fun saveExercise(name: String, muscleGroups: List<MuscleGroup>, goBack: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val exerciseId = exerciseDao.insertAll(Exercise(0, name))[0]
            muscleGroups.forEach {
                exerciseMuscleGroupDao.insertAll(MuscleGroupExerciseCrossRef(it.muscleGroupId, exerciseId))
            }
        }
        goBack()
    }
}