package com.alkema.fityou.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.alkema.fityou.domain.db.ExerciseWithMuscleGroups
import com.alkema.fityou.domain.db.entities.Exercise

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM Exercise")
    fun getAll(): List<Exercise>

    @Query("SELECT * FROM Exercise WHERE exerciseId = :id")
    fun getOne(id: Long): Exercise

    @Transaction
    @Query("SELECT * FROM Exercise")
    fun getExercisesWithMuscleGroups(): List<ExerciseWithMuscleGroups>


    @Insert
    fun insertAll(vararg exercises: Exercise): List<Long>

    @Delete
    fun delete(exercise: Exercise)
}