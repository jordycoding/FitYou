package com.alkema.fityou.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.alkema.fityou.domain.db.ExerciseWithMuscleGroups
import com.alkema.fityou.domain.db.entities.MuscleGroupExerciseCrossRef

@Dao
interface ExerciseMuscleGroupDao {
    @Transaction
    @Query("SELECT * FROM Exercise")
    fun getExerciseWithMuscleGroups(): List<ExerciseWithMuscleGroups>

    @Insert
    fun insertAll(vararg muscleGroupExerciseCrossRef: MuscleGroupExerciseCrossRef)
}