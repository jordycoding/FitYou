package com.alkema.fityou.ui.fitness.exercises

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alkema.fityou.data.db.MuscleGroupDao
import com.alkema.fityou.domain.db.entities.MuscleGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(private val muscleGroupDao: MuscleGroupDao): ViewModel(){
    private val _muscleGroups = mutableStateOf<List<MuscleGroup>>(listOf())
    val muscleGroups : State<List<MuscleGroup>> = _muscleGroups

    fun getMuscleGroups() {
        viewModelScope.launch(Dispatchers.IO)  {
            val result = muscleGroupDao.getAll()
            _muscleGroups.value = result
        }
    }
}