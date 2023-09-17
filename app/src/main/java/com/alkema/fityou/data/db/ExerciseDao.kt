package com.alkema.fityou.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.alkema.fityou.domain.db.entities.Exercise

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM Exercise")
    fun getAll(): List<Exercise>

    @Insert
    fun insertAll(vararg exercises: Exercise)

    @Delete
    fun delete(exercise: Exercise)
}