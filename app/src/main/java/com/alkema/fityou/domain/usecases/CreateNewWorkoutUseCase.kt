package com.alkema.fityou.domain.usecases

import android.util.Log
import com.alkema.fityou.data.db.WorkoutDao
import com.alkema.fityou.domain.db.entities.Workout
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar
import javax.inject.Inject

class CreateNewWorkoutUseCase @Inject constructor(
    private val workoutDao: WorkoutDao,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    /**
     * Create a new workout, if one exists for the current day return that one
     *
     * @param onePerDay Whether or not a workout should be created for the current day if one already exists in the database
     */
    suspend operator fun invoke(onePerDay: Boolean = true) = withContext(defaultDispatcher) {
        val now = Calendar.getInstance()
        now.set(Calendar.HOUR_OF_DAY, 0)
        now.clear(Calendar.MINUTE)
        now.clear(Calendar.SECOND)
        now.clear(Calendar.MILLISECOND)
        Log.d("Time", now.timeInMillis.toString())

        val workouts = workoutDao.getAll(now.timeInMillis)
        if ((workouts.isEmpty()) || !onePerDay) {
            val workout = Workout(0, System.currentTimeMillis())
            val result = workoutDao.insertAll(workout)
            return@withContext result[0]
        } else {
            return@withContext workouts[0].workoutId
        }
    }

}