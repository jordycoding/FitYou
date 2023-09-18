package com.alkema.fityou.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.alkema.fityou.domain.db.entities.Exercise
import dagger.Provides
import javax.inject.Singleton

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM Exercise")
    fun getAll(): List<Exercise>

    @Insert
    fun insertAll(vararg exercises: Exercise): List<Long>

    @Delete
    fun delete(exercise: Exercise)
}