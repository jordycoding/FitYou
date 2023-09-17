package com.alkema.fityou.ui.fitness.exercises.listView

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import com.alkema.fityou.data.db.AppDatabase
import com.alkema.fityou.data.db.ExerciseDao
import com.alkema.fityou.domain.db.entities.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExercisesListViewModel @Inject constructor(private val exerciseDao: ExerciseDao) :
    ViewModel() {
        private val _exercisesList = mutableStateOf<List<Exercise>>(listOf())
    val exercisesList: State<List<Exercise>> = _exercisesList

    fun getExercisesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = exerciseDao.getAll()
            _exercisesList.value = result
        }
    }
}