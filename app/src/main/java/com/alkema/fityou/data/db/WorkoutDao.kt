package com.alkema.fityou.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.alkema.fityou.domain.db.WorkoutWithEntries
import com.alkema.fityou.domain.db.entities.Workout

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM Workout")
    fun getAll(): List<Workout>

    @Transaction
    @Query("SELECT * FROM Workout")
    fun getWorkoutWithEntries(): List<WorkoutWithEntries>

    @Insert
    fun insertAll(vararg workout: Workout): List<Long>
}